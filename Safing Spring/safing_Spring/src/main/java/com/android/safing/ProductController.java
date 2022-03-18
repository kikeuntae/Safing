package com.android.safing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import common.CommonService;
import member.MemberDAO;
import member.MemberVO;
import product.AddressVO;
import product.CartVO;
import product.Order_Detail_CntVO;
import product.ProductDAO;
import product.ProductVO;
import product.Product_DetailVO;
import product.Product_PackageVO;
import product.Product_Package_DetailVO;
import product.PurchaseHistoryVO;
import product.ReviewVO;

@Controller
public class ProductController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private ProductDAO dao;

	
	//패키지 리스트
	@ResponseBody
	@RequestMapping("/package_list.sh")
	public void  package_list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		List<Product_PackageVO> list = dao.package_list();
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	
	//상품 리스트
	@ResponseBody
	@RequestMapping("/product_list.sh")
	public void  product_list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		List<ProductVO> list = dao.product_list(req.getParameter("search"));
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	
	//패키지 상세정보
	@ResponseBody
	@RequestMapping("/package_detail.sh")
	public void  package_detail(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int package_num = Integer.parseInt(req.getParameter("package_num"));
	
		Product_Package_DetailVO vo = dao.package_detail(package_num);
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(vo));
	}
	
	//상품 상세정보
	@ResponseBody
	@RequestMapping("/product_detail.sh")
	public void  product_detail(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int product_num = Integer.parseInt(req.getParameter("product_num"));
		
		Product_DetailVO vo = dao.product_detail(product_num);
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(vo));
	}

		
	//패키지 상품 상세정보 페이지
	@ResponseBody
	@RequestMapping("/product_details_page_pack.sh")
	public void  product_details_page_pack(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int package_num = Integer.parseInt(req.getParameter("num"));
		
		List<Product_DetailVO> list = dao.product_details_page_pack(package_num);
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}	
	
	
	
	//상품 리뷰리스트
	@ResponseBody
	@RequestMapping("/review_list_pro.sh")
	public void  review_list_pro(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int product_num = Integer.parseInt(req.getParameter("num"));
		
		List<ReviewVO> list = dao.review_list_pro(product_num);
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	
	//패키지 리뷰리스트
	@ResponseBody
	@RequestMapping("/review_list_pack.sh")
	public void  review_list_pack(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int package_num = Integer.parseInt(req.getParameter("num"));
		
		List<ReviewVO> list = dao.review_list_pack(package_num);
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	
	
	//리뷰 좋아요 수정
	@ResponseBody
	@RequestMapping("/board_like_cnt_update.sh")
	public void  board_like_cnt_update(HttpServletRequest req, HttpServletResponse res) throws Exception{
		ReviewVO vo = new ReviewVO();
		vo.setReview_num(Integer.parseInt(req.getParameter("review_num")));
		vo.setLike_cnt(Integer.parseInt(req.getParameter("like_cnt")));
		
		dao.board_like_cnt_update(vo);
	}
	
	//리뷰등록
	@ResponseBody
	@RequestMapping("/review_intsert.sh")
	public void  review_intsert(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int result = dao.review_intsert(gson.fromJson(req.getParameter("vo"), ReviewVO.class));
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(result));
	}
	//장바구니 리스트
	@ResponseBody
	@RequestMapping("/cart_list.sh")
	public void  cart_list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		List<CartVO> list = dao.cart_list(req.getParameter("member_id"));
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	
	//장바구니 담기 상품
	@ResponseBody
	@RequestMapping("/insert_cart_pro.sh")
	public void  insert_cart_pro(HttpServletRequest req, HttpServletResponse res) throws Exception{
		CartVO vo = gson.fromJson(req.getParameter("vo"), CartVO.class);
		
		int result = dao.insert_cart_pro(vo);
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(result));
	}
	
	//장바구니 담기 패키지
	@ResponseBody
	@RequestMapping("/insert_cart_pack.sh")
	public void  insert_cart_pack(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int idx = Integer.parseInt(req.getParameter("idx"));
		int priceSum = Integer.parseInt(req.getParameter("priceSum"));
		List<CartVO> list = new ArrayList<>();
		for(int i = 0 ; i < idx-1; i++) {
			list.add(gson.fromJson(req.getParameter("list"+i), CartVO.class));
		}
		
		int result = dao.insert_cart_pack(list, priceSum);
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(result));
	}	
	
	//장바구니 삭제
	@ResponseBody
	@RequestMapping("/delete_cart.sh")
	public void  delete_cart(HttpServletRequest req, HttpServletResponse res) throws Exception{
		dao.delete_cart(Integer.parseInt(req.getParameter("cart_num")));
	
	}
	
	//구매내역 리스트
	@ResponseBody
	@RequestMapping("/purchaseHistory_list.sh")
	public void  purchaseHistory_list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		List<PurchaseHistoryVO> list = dao.purchaseHistory_list(req.getParameter("member_id"));
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	
	//구매내역 환불여부
	@ResponseBody
	@RequestMapping("/update_refund.sh")
	public void  update_refund(HttpServletRequest req, HttpServletResponse res) throws Exception{
		dao.update_refund(gson.fromJson(req.getParameter("vo"), PurchaseHistoryVO.class));
		
	}
	
	
	//기본주소 불러오기
	@ResponseBody
	@RequestMapping("/default_addrss.sh")
	public void  default_addrss(HttpServletRequest req, HttpServletResponse res) throws Exception{
		AddressVO vo = dao.default_addrss(req.getParameter("member_id"));
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(vo));
	}
	
	//주소 리스트
	@ResponseBody
	@RequestMapping("/addrss_list.sh")
	public void  addrss_list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		List<AddressVO> list = dao.addrss_list(req.getParameter("member_id"));
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	
	//기본 주소 변경
	@ResponseBody
	@RequestMapping("/update_address.sh")
	public void  update_address(HttpServletRequest req, HttpServletResponse res) throws Exception{
		dao.update_address(Integer.parseInt(req.getParameter("addr_num")));
		
	}
	
	//주소 등록
	@ResponseBody
	@RequestMapping("/insert_address.sh")
	public void  insert_address(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int addr_num = dao.insert_address(gson.fromJson(req.getParameter("vo"), AddressVO.class), req.getParameter("member_id"));
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(addr_num));
	}
	
	
	//주소 삭제
	@ResponseBody
	@RequestMapping("/delete_addr.sh")
	public void  delete_addr(HttpServletRequest req, HttpServletResponse res) throws Exception{
		dao.delete_addr(Integer.parseInt(req.getParameter("addr_num")));
			
	}
	
	
	//결제하기 장바구니
	@ResponseBody
	@RequestMapping("/insert_order_ing_cart.sh")
	public void  insert_order_ing_cart(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int idx = Integer.parseInt(req.getParameter("idx"));
		AddressVO address = gson.fromJson(req.getParameter("address"), AddressVO.class);
		
		List<CartVO> list = new ArrayList<>();
		for(int i = 0 ; i < idx-1; i++) {
			list.add(gson.fromJson(req.getParameter("list"+i), CartVO.class));
		}
		
		int result = dao.insert_order_ing_cart(list, address);
		
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(result));
			
	}
	
	//결제하기 패키지
	@ResponseBody
	@RequestMapping("/insert_order_ing_pack.sh")
	public void  insert_order_ing_pack(HttpServletRequest req, HttpServletResponse res) throws Exception{
		int idx = Integer.parseInt(req.getParameter("idx"));
		AddressVO address = gson.fromJson(req.getParameter("address"), AddressVO.class);
		CartVO vo = gson.fromJson(req.getParameter("vo"), CartVO.class);
		
		List<Order_Detail_CntVO> list = new ArrayList<>();
		for(int i = 0 ; i < idx-2; i++) {
			list.add(gson.fromJson(req.getParameter("list"+i), Order_Detail_CntVO.class));
		}

		
		
		int result = dao.insert_order_ing_pack(vo, list, address);
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(result));
			
	}
	
	
	//결제하기 상품
	@ResponseBody
	@RequestMapping("/insert_order_ing_pro.sh")
	public void  insert_order_ing_pro(HttpServletRequest req, HttpServletResponse res) throws Exception{
		CartVO vo = gson.fromJson(req.getParameter("vo"), CartVO.class);
		AddressVO address = gson.fromJson(req.getParameter("address"), AddressVO.class);
		
		int result = dao.insert_order_ing_pro(vo, address);
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(result));
			
	}
}
