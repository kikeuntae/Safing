package com.android.safing;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import board.Board_FileVO;
import common.CommonService;
import common.ImginsertVO;
import common.InsertDAO;
import common.OutPrintln;
import common.Product_FileVO;
import common.ProductinVO;
import common.TagVO;
import common.ThemeRecDTO;
import member.MemberVO;
import product.ProductDAO;

@Controller
public class InsertController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private InsertDAO dao;
	
	//이미지 넣기
	@ResponseBody
	@RequestMapping ("/insert_img.bo")
	public String insert(ImginsertVO vo, MultipartFile file, HttpSession session) {
		
		// 파일 정보가 있다면
		if ( ! file.isEmpty() ) {
			vo.setPackage_name("겨울");
			vo.setFile_name( file.getOriginalFilename() );
			vo.setFile_path(service.fileupload("product_packge", file, session) );
		}
		
		boolean result = dao.img_insert(vo);
		return "redirect:/" + result;
		}
		
		//테그 넣기
		@ResponseBody
		@RequestMapping ("/insert_tag.bo")
		public String insert_tag(TagVO vo, MultipartFile file, HttpSession session) {
			vo.setPackage_num(8);
			vo.setTag_key("#루프탑#차박#차박텐트");
			boolean result = dao.tag_insert(vo);
			return "redirect:/" + result;
		}
		
		//상품 넣기
		@ResponseBody
		@RequestMapping ("/insert_pro.bo")
		public String insert_pro(ProductinVO vo, MultipartFile file, HttpSession session) {
			vo.setProduct_name("GLAMCAVE 바이오 에탄올난로 실내불멍 화로 가정용불멍");
			vo.setProduct_price(28000);
			vo.setProduct_stock(10);
			vo.setProduct_kind("감성용품");
			
			boolean result = dao.pro_insert(vo);
			return "redirect:/" + result;
		}
		
		//상품이미지 넣기
		@ResponseBody
		@RequestMapping ("/insert_profile.bo")
		public String insert_profile(Product_FileVO vo, MultipartFile file, HttpSession session) {
			
			// 파일 정보가 있다면
			if ( ! file.isEmpty() ) {
				vo.setProduct_num(109);
				vo.setFile_name( file.getOriginalFilename() );
				vo.setFile_path(service.fileupload("product_file", file, session) );
			}
			
			boolean result = dao.profile_insert(vo);
			return "redirect:/" + result;
		}
		
		//상품이미지 넣기
		@ResponseBody
		@RequestMapping ("/insert_member.bo")
		public String insert_member(MemberVO vo, MultipartFile file, HttpSession session) {
			
			// 파일 정보가 있다면
			if ( ! file.isEmpty() ) {
				vo.setMember_id("in2thefree");
				vo.setMember_pw("Su2351");
				vo.setMember_name("최성욱");
				vo.setMember_age(32);
				vo.setMember_gender("남");
				vo.setMember_phone("010-3865-2153");
				vo.setMember_admin("n");
				vo.setMember_filename(file.getOriginalFilename());
				vo.setMember_filepath(service.fileupload("member", file, session));
			}
			
			boolean result = dao.member_insert(vo);
			return "redirect:/" + result;
		}
		
		//동영상 넣기
		@ResponseBody
		@RequestMapping ("/insert_boardfile.bo")
		public String insert_boardfile(Board_FileVO vo, MultipartFile file, HttpSession session) {
			
			// 파일 정보가 있다면
			if ( ! file.isEmpty() ) {
				vo.setBoard_id(66);
				vo.setFile_name(file.getOriginalFilename());
				vo.setFile_path(service.fileupload("board_file", file, session));
			}
			
			boolean result = dao.insert_boardfile(vo);
			return "redirect:/" + result;
		}

		//테마 리스트
		@ResponseBody
		@RequestMapping("/Theme_Pager.home")
		public void  Theme_Pager_list(HttpServletRequest req, HttpServletResponse res) throws Exception{
			List<ThemeRecDTO> list = dao.Theme_Pager_list();
			req.setCharacterEncoding("UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html");
			PrintWriter writer = res.getWriter();
			writer.println( gson.toJson(list));
		}
}
