package com.android.safing;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import member.MemberVO;
import product.OrderManagePage;
import product.OrderManageVO;
import product.ProductImageVO;
import product.ProductManagePage;
import product.ProductManageVO;
import product.ProductPage;
import product.ShopDAO;

@Controller
public class ShopController {
	
	@Autowired private CommonService service;
	@Autowired private ShopDAO dao;
	List<ProductImageVO> newProductImgs = new ArrayList<ProductImageVO>();
	
	
	//쇼핑 메인페이지
	@RequestMapping ("/list.shop")
	public String shopmain(Model  model, HttpSession session, String search, String keyword
						, @RequestParam (defaultValue = "1") int curPage
						, @RequestParam (defaultValue = "12") int pageList) {
		session.setAttribute("category", "shop");
		
		MemberVO vo = new MemberVO();
		vo.setMember_admin("y");
		session.setAttribute("loginInfo",vo);
		
		ProductPage page = new ProductPage();
		
		page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
		page.setSearch(search);	// 검색 조건 값을 page에 담음	
		page.setKeyword(keyword);	// 검색 키워드 값을 page에 담음
		page.setPageList(pageList);	// 페이지당 보여질 글 목록 수를 page에 담음
		page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
		
		if(keyword != null) {
			page.setSearch(null);
		} else {
			page.setKeyword(null);
		}
		
		model.addAttribute("package_list", dao.package_list());
		model.addAttribute("page", dao.product_list(page));
		model.addAttribute("package_detail_list", dao.package_detail_list());
		model.addAttribute("product_detail_list", dao.product_detail_list(page));
		
		return "shop/shopmain";
	}
	

	
	//상품관리 페이지
	@RequestMapping ("/productmanage.shop")
	public String productmanage(Model  model, HttpSession session,String search, String keyword
			, @RequestParam (defaultValue = "1") int curPage
			, @RequestParam (defaultValue = "10") int pageList) {
		session.setAttribute("category", "shop");
		
		MemberVO vo = new MemberVO();
		vo.setMember_admin("y");
		session.setAttribute("loginInfo",vo);
		
		ProductManagePage page = new ProductManagePage();
		
		page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
		page.setSearch(search);	// 검색 조건 값을 page에 담음	
		page.setKeyword(keyword);	// 검색 키워드 값을 page에 담음
		page.setPageList(pageList);	// 페이지당 보여질 글 목록 수를 page에 담음
		page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
		
		if(keyword != null) {
			page.setSearch(null);
		} else {
			page.setKeyword(null);
		}
		
		
		model.addAttribute("page", dao.product_manage_list(page));
		
		return "shop/productmanage";
	}
	
	//상품관리 상품등록 페이지
	@RequestMapping ("/productnew.shop")
	public String productnew(HttpSession session) {
		session.setAttribute("category", "shop");
		
		MemberVO vo = new MemberVO();
		vo.setMember_admin("y");
		session.setAttribute("loginInfo",vo);

		
		return "shop/productnew";
	}
	
	//상품관리 수정
	@RequestMapping ("/product_update.shop")
	public String product_update(ProductManageVO vo) {
		dao.product_update(vo);
		
		return "redirect:productmanage.shop";
	}
	
	//상품관리 삭제
	@RequestMapping ("/product_delete.shop")
	public String product_delete(int product_num) {
		dao.product_delete(product_num);
		
		return "redirect:productmanage.shop";
	}
	
	//주문관리 페이지
	@RequestMapping ("/ordermanage.shop")
	public String ordermanage(Model  model, HttpSession session, String search, String keyword
			, @RequestParam (defaultValue = "1") int curPage
			, @RequestParam (defaultValue = "10") int pageList) {
		session.setAttribute("category", "shop");
		
		MemberVO vo = new MemberVO();
		vo.setMember_admin("y");
		session.setAttribute("loginInfo",vo);
		
		OrderManagePage page = new OrderManagePage();
		
		page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
		page.setSearch(search);	// 검색 조건 값을 page에 담음	
		page.setKeyword(keyword);	// 검색 키워드 값을 page에 담음
		page.setPageList(pageList);	// 페이지당 보여질 글 목록 수를 page에 담음
		page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
		
		if(keyword != null) {
			page.setSearch(null);
		} else {
			page.setKeyword(null);
		}
		
		model.addAttribute("page", dao.order_manage_list(page));
		
		return "shop/ordermanage";
	}
	
	//주문관리 수정
	@RequestMapping ("/order_update.shop")
	public String order_update(OrderManageVO vo) {
		dao.order_update(vo);
		
		return "redirect:ordermanage.shop";
	}
	

	//파일 다중 업로드 
	@ResponseBody
	@RequestMapping(value = "/file-upload.shop", method = RequestMethod.POST)
	public boolean fileUpload(
			@RequestParam("article_file") List<MultipartFile> multipartFile
			, HttpServletRequest request) {
		System.out.println("df");
		
		boolean strResult = false;
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		String fileRoot;
		
		try {
			// 파일이 있을때 탄다.
			if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
				
				for(int i = 0 ; i < multipartFile.size(); i++) {
					newProductImgs.add(new ProductImageVO());
					fileRoot = contextRoot + "resources/upload/";
					System.out.println(fileRoot);
					
					String originalFileName = multipartFile.get(0).getOriginalFilename();	//오리지날 파일명
					newProductImgs.get(i).setFile_name(originalFileName);
					
					String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
					String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
					
					File targetFile = new File(fileRoot + savedFileName);	
					newProductImgs.get(i).setFile_path("upload/" + savedFileName);
					
					try {
						InputStream fileStream = multipartFile.get(0).getInputStream();
						FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장
						
					} catch (Exception e) {
						//파일삭제
						FileUtils.deleteQuietly(targetFile);	//저장된 현재 파일 삭제
						e.printStackTrace();
						
						break;
					}
				}
				strResult = true;
			}
			// 파일 아무것도 첨부 안했을때 탄다.(게시판일때, 업로드 없이 글을 등록하는경우)
			else
				strResult = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return strResult;
	}
	
	
	//상품관리 등록
	@ResponseBody
	@RequestMapping(value = "/product_insert.shop", method = RequestMethod.POST)
	public boolean product_insert(ProductManageVO vo) {
		boolean strResult = false;
		
		int result = dao.product_insert(vo, newProductImgs);
		if(result >0) {
			strResult = true;
		}
		
		return strResult;
	}
	
}
