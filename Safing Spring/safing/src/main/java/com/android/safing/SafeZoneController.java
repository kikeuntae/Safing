package com.android.safing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import campinfo.CampImgDAO;
import common.CommonService;
import common.OutPrintln;
import safezone.SafeZoneDAO;
import safezone.SafeZoneVO;
import safezone.SearchPage;
import tip.YoutubeTipDAO;

@Controller
public class SafeZoneController {
	Gson gson = new Gson();

	@Autowired
	private CommonService service;
	@Autowired
	private OutPrintln outprintln;
	@Autowired
	private SafeZoneDAO dao;
	@Autowired
	private SearchPage page;
	@Autowired 
	private YoutubeTipDAO dao1;
	@Autowired 
	private CampImgDAO dao2;
	
	// safezone 가입 list
	@ResponseBody
	@RequestMapping("/sfzone_rec.home")
	public void list(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<SafeZoneVO> list = dao.list();
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println(gson.toJson(list));
	}

	// 캠핑장 검색 (안드)

	@ResponseBody
	@RequestMapping("/all_list.home")
	public void all_list(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String search = req.getParameter("search");
		List<SafeZoneVO> list = dao.all_list(search);
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println(gson.toJson(list));
	}
	
	
	// 캠핑장 목록화면 요청 (웹)
	@RequestMapping ("/search_list.ma")
	public String search_list(HttpSession session , Model model
			, String search, String keyword
			, @RequestParam (defaultValue = "1") int curPage
			, @RequestParam (defaultValue = "10") int pageList
			, @RequestParam (defaultValue = "list") String viewType ){
		
		// DB에서 방명록 정보를 조회해와 목록화면에 출력
		page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
		page.setSearch(search);		// 검색 조건 값을 page에 담음
		page.setKeyword(keyword);	// 검색 키워드 값을 page에 담음
		page.setPageList(pageList);	// 페이지당 보여질 글 목록 수를 page에 담음
		page.setViewType(viewType);	// 게시판 형태를 page에 담음
		session.setAttribute("category", "ma");
		
		model.addAttribute("page", dao.search_list(page));
		return "main/search_list";
	}
	
	// 캠핑장 정보 상세화면 요청
	
		@RequestMapping ("/detail.ma")
		public String detail(int contentid, Model model) {
			
			// 해당 방명록 글을 DB에서 조회해와 상세화면에 출력
			model.addAttribute("vo", dao.campinfo_detail(contentid) ) ;
			model.addAttribute("img_list", dao2.img_list(contentid) ) ;
			
			return "main/detail";
		}
	
	

}
