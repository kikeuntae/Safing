package com.android.safing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import board.Board_CommentVO;
import board.Board_FileVO;
import board.Board_MovieDTO;
import common.CommonService;
import common.OutPrintln;
import member.MemberDAO;
import member.MemberVO;
import tip.YoutubeTipVO;

@Controller
public class BoardController {
	Gson gson = new Gson();
	
	@Autowired private CommonService common;
	@Autowired private BoardPage page;
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private BoardDAO dao;

	// 게시판 글에 대한 댓글 삭제 처리 요청
	@ResponseBody
	@RequestMapping("/board/comment/delete/{id}")
	public void comment_delete(@PathVariable int id) {
		// 해당 댓글을 DB에서 삭제
		dao.board_comment_delete(id);
	}
	
	
	
	// 게시판 글에 대한 댓글 수정처리 요청
	// 컨트롤러를 통해 보내고 있는 응답의 유형을 나타내기 위해 produces 를 사용하여 한글 깨짐 해결
	// 이 "produces" 키워드는 ajax 요청에서 가장 유용하게 사용됨.
	@ResponseBody 
	@RequestMapping (value = "/board/comment/update", produces="application/text; charset=utf-8")
					// json 형태의 값을 vo 에 담기 위해선 @RequestBody 를 선언
	public String comment_update(@RequestBody Board_CommentVO vo) {
		return dao.board_comment_update(vo) == 1 ? "성공!!" : "실패!!";
	}
	
	
	// 게시판 글에 대한 댓글 목록조회 요청
	
	@RequestMapping("/board/comment/list/{pid}")
	public String comment_list(@PathVariable int pid, Model model) {
		// 해당 글에 대한 댓글들을 DB에서 조회해 온다.
		model.addAttribute("list", dao.board_comment_list(pid));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\n");
		return "board/comment/comment_list";
	}

	// 게시판 글에 대한 댓글 저장처리 요청
	@ResponseBody
	@RequestMapping ("/board/comment/regist")
	public boolean comment_regist(Board_CommentVO vo, HttpSession session) {		
		// 작성자의 경우 member 의 id 값을 담아야 하므로 로그인 정보 확인
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		vo.setMember_id(member.getMember_id());
		
		// 화면에서 입력한 댓글 정보를 DB에 저장한 후 저장 여부를 반환
		return dao.board_comment_insert(vo) == 1 ? true : false;
	}
	
	// 게시판 글 수정 저장처리 요청
	@RequestMapping("/update.bo")
	public String update(BoardVO vo, MultipartFile file, String attach
			, HttpSession session, Model model) {
		
		
		// 원 글에 첨부 파일이 있었는지 조회
		BoardVO board = dao.board_detail( vo.getBoard_id() );
		List<Board_FileVO> board_file = dao.board_file_select(vo.getBoard_id());
		
		
		if(!(board_file == null)) {
		for (int i = 0; i < board_file.size(); i++) {
			
		String uuid = session.getServletContext().getRealPath("resources") + "/" + board_file.get(i).getFile_path();
		
		// 파일을 첨부하지 않은 경우
		if ( file.isEmpty() ) {
			// 원래부터 첨부된 파일이 없는 경우
			// 원래 첨부된 파일이 있었는데 삭제한 경우
			if ( attach.isEmpty() ) {
				// 원래 첨부되어 있는 파일이 있다면 서버의 물리적 영역에서 삭제
				if ( board_file.get(i).getFile_name() != null) {
					File f = new File(uuid); 
					if (f.exists() ) f.delete();	// 파일이 존재하면 파일을 삭제 처리
				}				
			} else { // 원래 첨부된 파일을 그대로 사용하는 경우
				vo.setFile_name(board_file.get(i).getFile_name() );
				vo.setFile_path(board_file.get(i).getFile_path() );				
			}
		} else { // 파일을 첨부한 경우
			vo.setFile_name( file.getOriginalFilename() );
			vo.setFile_path( common.fileupload("board", file, session) );
			board_file.get(i).setFile_name( file.getOriginalFilename() );
			board_file.get(i).setFile_path(common.fileupload("board", file, session) );
			dao.board_update_img(board_file.get(i));
			// 원래 첨부되어 있는 파일이 있다면 서버의 물리적 영역에서 삭제
			if ( board_file.get(i).getFile_name() != null) {
				File f = new File(uuid); 
				if (f.exists() ) f.delete();	// 파일이 존재하면 파일을 삭제 처리
			}
		}
		
		
		}	//for
		}//if
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		vo.setMember_id(member.getMember_id());
		
		
		// 화면에서 수정한 정보들을 DB에서 저장한 후 상세화면 연결
		
		model.addAttribute("uri", "detail.bo");
		model.addAttribute("id", vo.getBoard_id());
		dao.board_update(vo);
		return "board/redirect";
	}//update
	
	
	
	// 게시판 글 수정 화면 요청
	@RequestMapping("/modify.bo")
	public String modify(int id, Model model) {
		// 해당 글의 정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo", dao.board_detail(id) ) ;
		return "board/modify";
	}
	
	// 게시판 글 삭제처리 요청
	@RequestMapping("/delete.bo")
	public String delete(int id, HttpSession session, Model model) {
		// 첨부 파일이 있는 글에 대해서는 해당 파일을 서버의 물리적 영역에서 삭제
		BoardVO vo = dao.board_detail(id);
		if ( vo.getFile_path() != null) {
			File file = new File( session.getServletContext().getRealPath("resources") 
					+ "/" + vo.getFile_path() );
			if ( file.exists() ) file.delete();
		}
		
		// 해당 게시판 글을 DB에서 삭제한 후 목록화면으로 연결
		dao.board_delete(id);
	//	return "redirect:list.bo";
		model.addAttribute("uri", "list.bo");
		model.addAttribute("page", page);
		return "board/redirect";
	}
	
	
	// 게시판 첨부파일 다운로드 요청
	@RequestMapping ("/download.bo")
	public void download(int id, HttpSession session, HttpServletResponse response) {
		// 해당 글의 첨부 파일 정보를 DB에서 조회해
		BoardVO vo = dao.board_detail(id);		
		// 해당 파일을 서버로부터 다운로드 함.
		common.fileDownload(vo.getFile_name(), vo.getFile_path(), session, response);
		// 클라이언트에 응답을 해줘야 하기 때문에 response 전달		
	}
		
	// 게시판 상세화면 요청
	@RequestMapping ("/detail.bo")
	public String detail(int id
			, Model model) {
		
		// 상세화면 요청 전 조회수 증가
		dao.board_read(id);
	
		// 해당 게시판 글을 DB에서 조회해와 상세화면에 출력
		model.addAttribute("vo", dao.board_detail(id) ) ;
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		return "board/detail";
	}
	
	
	// 게시판 신규 저장 처리 요청
	@RequestMapping ("/insert.bo")
	public String insert(BoardVO vo, MultipartFile file, HttpSession session) {
		

		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		vo.setMember_id(member.getMember_id()+"");
		dao.board_insert(vo);
		
		// 파일 정보가 있다면
		if ( ! file.isEmpty() ) {
			vo.setBoard_id(dao.board_new());
			vo.setFile_name( file.getOriginalFilename() );
			vo.setFile_path( common.fileupload("board_file", file, session) );
			dao.board_insert_img(vo);
		}
			
		
		return "redirect:list.bo";
	}
	
	
	// 게시판 신규 글 작성 화면 요청
	@RequestMapping ("/new.bo")
	public String board() {
		return "board/new";
	}
	
	// 게시판 목록화면 요청
		@RequestMapping ("/list.bo")
		public String list(HttpSession session , Model model
				,String search
				,String keyword
				, @RequestParam (defaultValue = "1") int curPage
				, @RequestParam (defaultValue = "10") int pageList
				, @RequestParam (defaultValue = "list") String viewType ) {
			session.setAttribute("category", "bo");
			
			// DB에서 게시판 정보를 조회해와 목록화면에 출력
			page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
			page.setSearch(search);		// 검색 조건 값을 page에 담음
			page.setKeyword(keyword);	// 검색 키워드 값을 page에 담음
			page.setPageList(pageList);	// 페이지당 보여질 글 목록 수를 page에 담음
			page.setViewType(viewType);	// 게시판 형태를 page에 담음
			
			//귀찮으니 회원로그인 고정 값처리
			MemberVO vo = new MemberVO();
			MemberDAO member_dao = new MemberDAO();
			vo.setMember_id("master");
			vo  = dao.login(vo);
			session.setAttribute("loginInfo", vo);
			
			
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
	
	// 유튜브 목록화면 요청
			@RequestMapping ("/list.yu")
			public String list_yu(HttpSession session , Model model
					,String search
					,String keyword
					, @RequestParam (defaultValue = "1") int curPage
					, @RequestParam (defaultValue = "10") int pageList
					, @RequestParam (defaultValue = "list") String viewType ) {
				
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
				vo  = dao.login(vo);
				session.setAttribute("loginInfo", vo);
				
				
				model.addAttribute("page", dao.board_list_youtube(page));		
				return "youtube/list";
			}	


			// 유튜브 상세화면 요청
			@RequestMapping ("/detail.yu")
			public String detail_yu(int id
					, Model model) {
				
				// 상세화면 요청 전 조회수 증가
				dao.board_read(id);
			
				// 해당 게시판 글을 DB에서 조회해와 상세화면에 출력
				model.addAttribute("vo", dao.board_detail(id) ) ;
				model.addAttribute("crlf", "\r\n");
				model.addAttribute("page", page);
				return "youtube/detail";
			}
			


			// 유튜브 글 삭제처리 요청
			@RequestMapping("/delete.yu")
			public String delete_yu(int id, HttpSession session, Model model) {
				// 첨부 파일이 있는 글에 대해서는 해당 파일을 서버의 물리적 영역에서 삭제
				BoardVO vo = dao.board_detail(id);
				if ( vo.getFile_path() != null) {
					File file = new File( session.getServletContext().getRealPath("resources") 
							+ "/" + vo.getFile_path() );
					if ( file.exists() ) file.delete();
				}
				
				// 해당 게시판 글을 DB에서 삭제한 후 목록화면으로 연결
				dao.board_delete(id);
			//	return "redirect:list.bo";
				model.addAttribute("uri", "list.yu");
				model.addAttribute("page", page);
				return "board/redirect";
			}
	


	
	
	
}

