package com.android.safing;

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

import board.BoardDAO;
import board.BoardPage;
import common.CommonService;
import common.OutPrintln;
import member.MemberDAO;
import member.MemberVO;
import tip.YoutubeTipDAO;
import tip.YoutubeTipVO;

@Controller
public class TipController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private YoutubeTipDAO dao;
	@Autowired private BoardDAO board_dao;
	@Autowired private BoardPage page;

	//===============================================안드===============================================//
	//tip 리스트
	@ResponseBody
	@RequestMapping("/tip_rec.home")
	public void  tip_list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		List<YoutubeTipVO> list = dao.tip_list();
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	
	//tip 조회수 증가
		@ResponseBody
		@RequestMapping("/tip_readcnt.home")
		public void  list_cnt(HttpServletRequest req, HttpServletResponse res) throws Exception{
			
			String str = req.getParameter("id");
			int readcnt = gson.fromJson(str, int.class);
			dao.cnt(readcnt);
			
			/*
			 * req.setCharacterEncoding("UTF-8"); res.setCharacterEncoding("UTF-8");
			 * res.setContentType("text/html"); PrintWriter writer = res.getWriter();
			 * writer.println( gson.toJson(list));
			 */
		}
		//===============================================안드===============================================//		
	
		
		
		
		//===============================================웹===============================================//	
		

		// 유튜브 목록화면 요청
				@RequestMapping ("/list.yu")
				public String list_yu(HttpSession session , Model model
						,String search
						,String keyword
						, @RequestParam (defaultValue = "1") int curPage
						, @RequestParam (defaultValue = "10") int pageList
						, @RequestParam (defaultValue = "grid") String viewType ) {
					
					session.setAttribute("category", "yu"); // category 에 cu 를 설정
					
					// DB에서 공지사항 정보를 조회해와 목록화면에 출력
					page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
					page.setSearch(search);		// 검색 조건 값을 page에 담음
					page.setKeyword(keyword);	// 검색 키워드 값을 page에 담음
					page.setPageList(pageList);	// 페이지당 보여질 글 목록 수를 page에 담음
					page.setViewType(viewType);	// 게시판 형태를 page에 담음
					
					//귀찮으니 회원로그인 고정 값처리
					MemberVO vo = new MemberVO();
					MemberDAO member_dao = new MemberDAO();
					vo.setMember_id("master");
					vo  = board_dao.login(vo);
					session.setAttribute("loginInfo", vo);
					
					
					model.addAttribute("page", dao.board_list_youtube(page));		
					return "youtube/list";
				}	

				// 유튜브 수정 화면 요청
				@RequestMapping("/modify.yu")
				public String modify(int id, Model model) {

					model.addAttribute("vo", dao.detail(id));
					return "youtube/modify_youtub";
				} 
				
				// 유튜브 수정 후 저장처리
				@RequestMapping("/update.yu")
				public String update(YoutubeTipVO vo, String attach
						, HttpSession session, Model model) {				

					// 화면에서 수정한 정보들을 DB에서 저장한 후 상세화면 연결
					dao.update(vo);
					model.addAttribute("uri", "detail.yu");
					model.addAttribute("id", vo.getId());
					return "youtube/redirect";
				}

				
				// 유튜브 상세화면 요청				
				@RequestMapping("/detail.yu")
				public String detail(int id, Model model) {
					// 해당 게시판 글을 DB에서 조회해와 상세화면에 출력
					 YoutubeTipVO  vo =  dao.detail(id);
					model.addAttribute("vo", dao.detail(id));
					model.addAttribute("crlf", "\r\n");
					model.addAttribute("page", page);
					return "youtube/detail";
				}
				
				// 유튜브 신규 작성 화면 요청
				@RequestMapping ("/new.yu")
				public String insert_new() {
					return "youtube/new_youtube";
				}
				
				
				// 유튜브 신규 저장 처리 요청
				@RequestMapping ("/insert.yu")
				public String insert(YoutubeTipVO vo, HttpSession session) {
										
					/*
					 * MemberVO member = (MemberVO) session.getAttribute("loginInfo"); vo.setWriter(
					 * member.getId() );
					 */
					
					dao.insert(vo);
					return "redirect:list.yu";
				}
				
				// 유튜브  글 삭제처리 요청
				@RequestMapping("/delete.yu")
				public String delete(int id, HttpSession session, Model model) {
					
					// 해당 방명록 글을 DB에서 삭제한 후 목록화면으로 연결
					dao.delete(id);
				//	return "redirect:list.bo";
					model.addAttribute("uri", "list.yu");
					model.addAttribute("page", page);
					return "youtube/redirect";
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
		
		//===============================================웹===============================================//
}
