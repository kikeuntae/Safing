package product;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import member.MemberVO;

@Service
public class ProductDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;

	//패키지 리스트
	public List<Product_PackageVO> package_list(){
		return sql.selectList("product.mapper.package_list");
	}
	
	//상품 리스트
	public List<ProductVO> product_list(String search){
		return sql.selectList("product.mapper.product_list", search);
	}
	
	//패키지 상세정보
	public Product_Package_DetailVO package_detail(int package_num){
		Product_Package_DetailVO vo = sql.selectOne("product.mapper.package_detail", package_num);		
		vo.setImagelist(sql.selectList("product.mapper.package_imagelist", package_num));
		vo.setKindlist(sql.selectList("product.mapper.package_kindlist", package_num));
		return vo;
	}
	
	//상품 상세정보
	public Product_DetailVO product_detail(int product_num){
		Product_DetailVO vo = sql.selectOne("product.mapper.product_detail", product_num);
		vo.setImagelist(sql.selectList("product.mapper.product_imagelist", product_num));
		return vo;
	}
	
	//패키지 상세정보 페이지
	public List<Product_DetailVO> product_details_page_pack(int package_num){
		return sql.selectList("product.mapper.product_details_page_pack", package_num);
	}
	
	//상품 리뷰리스트
	public List<ReviewVO> review_list_pro(int product_num ){
		List<ReviewVO> list = sql.selectList("product.mapper.review_list_pro", product_num);
		for (ReviewVO vo : list) {
			vo.setImagelist(sql.selectList("product.mapper.review_imagelist", vo.getReview_num()));
		}
		return list;
	}
	
	//패키지 리뷰리스트
	public List<ReviewVO> review_list_pack(int package_num ){
		List<ReviewVO> list = sql.selectList("product.mapper.review_list_pack", package_num);
		for (ReviewVO vo : list) {
			vo.setImagelist(sql.selectList("product.mapper.review_imagelist", vo.getReview_num()));
		}
		return list;
	}
	
	//리뷰 좋아요 수정
	public void board_like_cnt_update(ReviewVO vo) {
		sql.update("product.mapper.board_like_cnt_update", vo);
	}
	
	//리뷰등록
	public int review_intsert(ReviewVO vo) {
		int result = 0;
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		results.add(sql.insert("product.mapper.review_intsert", vo));
		
		vo.setBoard_id(sql.selectOne("product.mapper.select_board_num", vo.getMember_id()));
		results.add(sql.insert("product.mapper.review_intsert_product", vo.getBoard_id()));
		
		vo.setReview_num(sql.selectOne("product.mapper.select_review_num"));
		results.add(sql.insert("product.mapper.review_intsert_imageList", vo));
		
		
		for(int i : results) {
			if(i > 0) {
				result = i;
			} else {
				result = 0;
				break;
			}
		}
		return result;
	}
	
	//장바구니 리스트
	public List<CartVO> cart_list(String member_id) {
		List<CartVO> list = sql.selectList("product.mapper.cart_list", member_id);
		
		for(int i = 0 ; i < list.size(); i ++) {
			if(list.get(i).getProduct_num() > 0) {
				list.get(i).setFile_path(sql.selectOne("product.mapper.cart_pro_file_path", list.get(i).getProduct_num()));
			} else {
				list.get(i).setFile_path(sql.selectOne("product.mapper.cart_pack_file_path", list.get(i).getPackage_num()));
			}
		}
		
		return list; 
	}

	//장바구니 담기 상품
	public int insert_cart_pro(CartVO vo){
		return sql.insert("product.mapper.insert_cart_pro", vo);
	}
	
	
	//장바구니 담기 패키지
	public int insert_cart_pack(List<CartVO> list, int priceSum){
		int result = 0;
		ArrayList<Integer> results = new ArrayList<Integer>();
		list.get(0).setProduct_price(priceSum);
		CartVO cartvo = new CartVO(); 
		int cart_num = sql.selectOne("product.mapper.select_cart_num", list.get(0).getMember_id());		
		results.add(sql.insert("product.mapper.insert_cart_pack", list.get(0)));
		for (CartVO vo : list) {
			vo.setCart_num(cart_num);
			results.add(sql.insert("product.mapper.insert_cart_pack_detail", vo));
		}
		
		for(int i : results) {
			if(i > 0) {
				result = i;
			} else {
				result = 0;
				break;
			}
		}
		return result;
	}
	
	//장바구니 삭제
	public void delete_cart(int cart_num) {
		sql.update("product.mapper.delete_cart", cart_num);
	}
	
	//구매내역 리스트
	public List<PurchaseHistoryVO> purchaseHistory_list(String member_id) {
		List<PurchaseHistoryVO> list = sql.selectList("product.mapper.purchaseHistory_list", member_id);
		for(int i = 0 ; i < list.size(); i ++) {
			if(list.get(i).getProduct_num() > 0) {
				list.get(i).setFile_path(sql.selectOne("product.mapper.purchaseHistory_pro_file_path", list.get(i).getProduct_num()));
			} else {
				list.get(i).setFile_path(sql.selectOne("product.mapper.purchaseHistory_pack_file_path", list.get(i).getPackage_num()));
			}
		}
		
		return list; 
	}
	
	//구매내역 환불여부
	public void update_refund(PurchaseHistoryVO vo) {
		if(vo.getOrder_state_num() == 3) {
			vo.setOrder_state_num(3);
			sql.update("product.mapper.update_refund",vo);
			sql.update("product.mapper.update_refund_result",vo);
		} else if(vo.getOrder_state_num() == 4){
			vo.setOrder_state_num(4);
			sql.update("product.mapper.update_refund",vo);
			sql.update("product.mapper.update_refund_result",vo);
		}
		
	}
	
	
	
	//기본주소 불러오기
	public AddressVO default_addrss(String member_id) {
		return sql.selectOne("product.mapper.default_addrss", member_id);
	}
	
	
	//주소 리스트
	public List<AddressVO> addrss_list(String member_id) {
		return sql.selectList("product.mapper.addrss_list", member_id);
	}
	
	//기본 주소 변경
	public void update_address(int addr_num) {
		sql.delete("product.mapper.update_address_other");
		sql.delete("product.mapper.update_address", addr_num);
	}
	
	//주소 등록
	public void insert_address(AddressVO vo, String member_id) {
		sql.insert("product.mapper.insert_address", vo);
		sql.insert("product.mapper.insert_address_sub", member_id);
	}
	
	//주소 삭제
	public void delete_addr(int addr_num) {
		sql.delete("product.mapper.delete_addr", addr_num);
	}
	
}
