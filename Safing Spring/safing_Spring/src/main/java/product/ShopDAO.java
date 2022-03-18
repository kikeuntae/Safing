package product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import board.BoardPage;

@Service
public class ShopDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	//패키지 리스트
	public List<Product_PackageVO> package_list(){
		return sql.selectList("shop.mapper.package_list");
	}
	
	//상품 리스트
	public ProductPage product_list(ProductPage page) {
		 
		
		// 전체 상품 수 조회
		page.setTotalList(sql.selectOne("shop.mapper.totalList", page));
		// 페이징 처리된 전체 상품 목록 조회
		page.setList( sql.selectList("shop.mapper.product_list", page) );
		
		return page;
	}
	
	//패키지 상세 리스트
	public List<Product_Package_DetailVO> package_detail_list() {
		//패키지 상세 리스트
		List<Product_Package_DetailVO> list = sql.selectList("shop.mapper.package_detail");
		for(Product_Package_DetailVO vo : list) {
			//패키지 상세이미지
			vo.setImagelist(sql.selectList("shop.mapper.package_imagelist", vo.getPackage_num()));
			//패키지 상세 상품종류
			vo.setKindlist(sql.selectList("shop.mapper.package_kindlist", vo.getPackage_num()));;
		}
		
		return list;
	}
	
	//상품 상세 리스트
	public List<Product_DetailVO> product_detail_list(ProductPage page) {
    List<Product_DetailVO> list = sql.selectList("shop.mapper.product_detail", page);
    
    for(Product_DetailVO vo : list) {
    	vo.setImagelist(sql.selectList("shop.mapper.product_imagelist", vo.getProduct_num()));
    }
	 
    return list; }

	    
	//상품관리 페이지
    public ProductManagePage product_manage_list(ProductManagePage page) {
    	// 전체 상품 수 조회
		page.setTotalList(sql.selectOne("shop.mapper.totalList", page));
		// 페이징 처리된 전체 상품 목록 조회
		page.setList( sql.selectList("shop.mapper.product_manage_list", page));
		
	    return page;
    }
    
    //상품관리 상품등록 
    public int product_insert(ProductManageVO vo, List<ProductImageVO> imgs) {
    	int result = 0;
		ArrayList<Integer> results = new ArrayList<Integer>();
    	
    	int product_num = sql.selectOne("shop.mapper.select_product_num");
    	vo.setProduct_num(product_num+1);
    	results.add(sql.insert("shop.mapper.product_insert", vo));
    	
    	for(ProductImageVO imgVo : imgs) {
    		imgVo.setProduct_num(vo.getProduct_num());
    		results.add(sql.insert("shop.mapper.product_image_insert", imgVo));
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
    
    //상품관리 수정 
    public void product_update(ProductManageVO vo) {
    	sql.update("shop.mapper.product_update", vo);
	}
    
    
    //상품관리 삭제
  	public void product_delete(int product_num) {
  		sql.delete("shop.mapper.product_image_delete", product_num);
  		sql.delete("shop.mapper.product_delete", product_num);
  	}
    
    
	
    //주문관리 페이지
    public OrderManagePage order_manage_list(OrderManagePage page) {
    	// 전체 주문관리 수 조회
    	page.setTotalList(sql.selectOne("shop.mapper.order_totalList", page));
    	// 페이징 처리된 전체 주문관리 목록 조회
    	page.setList( sql.selectList("shop.mapper.order_manage_list", page));

    	return page;
    }
    
    //주문관리 수정 
    public void order_update(OrderManageVO vo) {
    	//order_ing 정보수정
    	sql.update("shop.mapper.order_update", vo);
    	int cnt = sql.selectOne("shop.mapper.order_result_select", vo.getOrder_num());
    	
    	//order_result 정보등록
    	if(cnt == 0 && vo.getOrder_state_num() > 2) {
    		sql.update("shop.mapper.order_result_insert", vo);    			
    	}
	}
    
}
