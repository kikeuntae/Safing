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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import board.BoardDAO;
import board.BoardPage;
import board.BoardVO;
import board.Board_MovieDTO;
import common.CommonService;
import common.OutPrintln;
import member.MemberDAO;
import member.MemberVO;

@Controller
public class BoardController {
	Gson gson = new Gson();
	
	@Autowired private BoardPage page;
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private BoardDAO dao;

	// 방명록 목록화면 요청
		@RequestMapping ("/list.bo")
		public String list(HttpSession session , Model model
				, String search, String keyword
				, @RequestParam (defaultValue = "1") int curPage
				, @RequestParam (defaultValue = "10") int pageList
				, @RequestParam (defaultValue = "list") String viewType ) {
			session.setAttribute("category", "bo");
			
			// DB에서 방명록 정보를 조회해와 목록화면에 출력
			page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
			page.setSearch(search);		// 검색 조건 값을 page에 담음
			page.setKeyword(keyword);	// 검색 키워드 값을 page에 담음
			page.setPageList(pageList);	// 페이지당 보여질 글 목록 수를 page에 담음
			page.setViewType(viewType);	// 게시판 형태를 page에 담음
			
			model.addAttribute("page", dao.board_list(page));		
			return "board/list";
		}
	
	
	
	//동영상 등록
	@ResponseBody
	@RequestMapping("/movieinsert.bo")
	public void join(HttpServletRequest req, HttpServletResponse res, HttpSession session, MultipartFile file) throws Exception {
	PrintWriter writer = outprintln.outprintln(req, res);	
	String strVo = req.getParameter("vo");	
	Board_MovieDTO vo = gson.fromJson(strVo, Board_MovieDTO.class);
	vo.setBoard_kinds("video");
	
	dao.movie_create(vo);
	
	
	
	
	//int file_id = dao.file_select(vo); 
	
	/*
	 * if ( ! file.isEmpty() ) { vo.setBoard_id(file_id);
	 * vo.setFile_name(file.getOriginalFilename());
	 * vo.setFile_path(service.fileupload("board_file", file, session)); }
	 */
	
	}

	//동영상 정보 목록
	@ResponseBody
	@RequestMapping("/movielist.bo")
	public void  list(HttpServletRequest req, HttpServletResponse res) throws Exception{

		List<Board_MovieDTO> list = dao.movielist();
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
		
	}

	
	//동영상 정보수정
	@ResponseBody
	@RequestMapping("/movieupdate.bo")
	public void  update(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
		String strVo = req.getParameter("vo");	
		Board_MovieDTO vo = gson.fromJson(strVo, Board_MovieDTO.class);
		
		dao.movie_update(vo);
		
	}
	
	//동영상 삭제
	@ResponseBody
	@RequestMapping("/moviedelete.bo")
	public void  delete(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
		String strVo = req.getParameter("vo");	
		Board_MovieDTO vo = gson.fromJson(strVo, Board_MovieDTO.class);
		
		System.out.println("test");
		
		dao.movie_delete(vo);	
	}
	

	//동영상 좋아요 처리
	@ResponseBody
	@RequestMapping("/like.bo")
	public void  like(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
		String strVo = req.getParameter("vo");	
		Board_MovieDTO vo = gson.fromJson(strVo, Board_MovieDTO.class);
	
		
		dao.board_like(vo);	
	}
	
	
	//동영상 댓글 수 새로고침 
	@ResponseBody
	@RequestMapping("/movie_comment_cnt.bo")
	public void  movie_comment_cnt(HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		int comment_cnt=0;
		String strVo = req.getParameter("id");	
		int id = gson.fromJson(strVo, int.class);
		comment_cnt = dao.movie_comment_cnt(id);	
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(comment_cnt));
	}
	
	
	
	


	
	
	
}
