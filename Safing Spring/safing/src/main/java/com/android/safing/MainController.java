package com.android.safing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import common.CommonService;
import common.OutPrintln;
import safezone.SafeZoneDAO;
import safezone.SearchPage;
import tip.YoutubeTipDAO;

@Controller
public class MainController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private SafeZoneDAO dao;
	@Autowired private YoutubeTipDAO dao1;
	@Autowired private SearchPage page;

	
	
	
	// main화면 요청
	@RequestMapping ("/home.ma")
	public String board(Model  model) {
		model.addAttribute("list", dao.list());
		model.addAttribute("tip_list", dao1.tip_list());
		return "main/home";
	}
	
	/*
	 * // 캠핑장 검색
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping ("/all_list.home") public String all_list(HttpServletRequest
	 * req, HttpServletResponse res) throws IOException { //name, email, phone 어떤
	 * 검색어를 넣어도 String search = req.getParameter("search"); List<SafeZoneVO> list =
	 * dao.all_list(search); req.setCharacterEncoding("utf-8");
	 * res.setCharacterEncoding("utf-8"); res.setContentType("text/html");
	 * PrintWriter writer = res.getWriter(); writer.println(gson.toJson(list));
	 * 
	 * return "main/search_list";
	 * 
	 * }
	 */
	
		/*// 방명록 목록화면 요청
		@RequestMapping ("/list.bo")
		public String list(HttpSession session , Model model
				, String search, String keyword
				, @RequestParam (defaultValue = "1") int curPage
				, @RequestParam (defaultValue = "10") int pageList
				, @RequestParam (defaultValue = "list") String viewType ) {
			;
			
			// DB에서 방명록 정보를 조회해와 목록화면에 출력
			page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
			page.setSearch(search);		// 검색 조건 값을 page에 담음
			page.setKeyword(keyword);	// 검색 키워드 값을 page에 담음
			page.setPageList(pageList);	// 페이지당 보여질 글 목록 수를 page에 담음
			page.setViewType(viewType);	// 게시판 형태를 page에 담음
			
			model.addAttribute("page", service.board_list(page));		
			return "board/list";
		}*/
		
		
	
}
