package com.android.safing;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
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

import com.google.gson.Gson;

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


@Controller
public class NoticeController {
	Gson gson = new Gson();
	
	@Autowired private CommonService common;
	@Autowired private BoardPage page;
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private BoardDAO dao;
	
	// 공지사항 글 수정 저장처리 요청
	@RequestMapping("/update.no")
	public String update(BoardVO vo, MultipartFile file, String attach
			, HttpSession session, Model model) {
		
		
		// 원 글에 첨부 파일이 있었는지 조회
		BoardVO board = dao.board_detail( vo.getBoard_id() );
		Board_FileVO board_file = dao.board_file_select(vo.getBoard_id());
		

			
		String uuid = session.getServletContext().getRealPath("resources") + "/" + board_file.getFile_path();
		
		// 파일을 첨부하지 않은 경우
		if ( file.isEmpty() ) {
			// 원래부터 첨부된 파일이 없는 경우
			// 원래 첨부된 파일이 있었는데 삭제한 경우
			if ( attach.isEmpty() ) {
				// 원래 첨부되어 있는 파일이 있다면 서버의 물리적 영역에서 삭제
				if ( board_file.getFile_name() != null) {
					File f = new File(uuid); 
					if (f.exists() ) f.delete();	// 파일이 존재하면 파일을 삭제 처리
				}				
			} else { // 원래 첨부된 파일을 그대로 사용하는 경우
				vo.setFile_name(board_file.getFile_name() );
				vo.setFile_path(board_file.getFile_path() );				
			}
		} else { // 파일을 첨부한 경우
			vo.setFile_name( file.getOriginalFilename() );
			vo.setFile_path( common.fileupload("board", file, session) );
			board_file.setFile_name( file.getOriginalFilename() );
			board_file.setFile_path(common.fileupload("board", file, session) );
			dao.board_update_img(board_file);
			// 원래 첨부되어 있는 파일이 있다면 서버의 물리적 영역에서 삭제
			if ( board_file.getFile_name() != null) {
				File f = new File(uuid); 
				if (f.exists() ) f.delete();	// 파일이 존재하면 파일을 삭제 처리
			}
		}
		
		
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		vo.setMember_id(member.getMember_id());
		
		
		// 화면에서 수정한 정보들을 DB에서 저장한 후 상세화면 연결
		
		model.addAttribute("uri", "detail.no");
		model.addAttribute("id", vo.getBoard_id());
		dao.board_update(vo);
		return "notice/redirect";
	}//update
	
	
	
	// 공지사항 글 수정 화면 요청
	@RequestMapping("/modify.no")
	public String modify(int id, Model model) {
		// 해당 글의 정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo", dao.board_detail(id) ) ;
		return "notice/modify";
	}
	
	// 공지사항 글 삭제처리 요청
	@RequestMapping("/delete.no")
	public String delete(int id, HttpSession session, Model model) {
		// 첨부 파일이 있는 글에 대해서는 해당 파일을 서버의 물리적 영역에서 삭제
		BoardVO vo = dao.board_detail(id);
		if ( vo.getFile_path() != null) {
			File file = new File( session.getServletContext().getRealPath("resources") 
					+ "/" + vo.getFile_path() );
			if ( file.exists() ) file.delete();
		}
		
		// 해당 공지사항 글을 DB에서 삭제한 후 목록화면으로 연결
		dao.board_delete(id);
	//	return "redirect:list.bo";
		model.addAttribute("uri", "list.no");
		model.addAttribute("page", page);
		return "notice/redirect";
	}
	
	
	// 공지사항 첨부파일 다운로드 요청
	@RequestMapping ("/download.no")
	public void download(int id, HttpSession session, HttpServletResponse response) {
		// 해당 글의 첨부 파일 정보를 DB에서 조회해
		BoardVO vo = dao.board_detail(id);		
		// 해당 파일을 서버로부터 다운로드 함.
		common.fileDownload(vo.getFile_name(), vo.getFile_path(), session, response);
		// 클라이언트에 응답을 해줘야 하기 때문에 response 전달		
	}
		
	// 공지사항 상세화면 요청
	@RequestMapping ("/detail.no")
	public String detail(int id
			, Model model) {
		
		// 상세화면 요청 전 조회수 증가
		dao.board_read(id);
	
		// 해당 공지사항 글을 DB에서 조회해와 상세화면에 출력
		model.addAttribute("vo", dao.board_detail(id) ) ;
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		return "notice/detail";
	}
	
	
	// 공지사항 신규 저장 처리 요청
	@RequestMapping ("/insert.no")
	public String insert(BoardVO vo, MultipartFile file, HttpSession session) {
		
		// 파일 정보가 있다면
		if ( ! file.isEmpty() ) {
			vo.setFile_name( file.getOriginalFilename() );
			vo.setFile_path( common.fileupload("board", file, session) );
			dao.board_insert_img(vo);
		}
		MemberVO member = (MemberVO) session.getAttribute("loginInfo");
		vo.setMember_id(member.getMember_id()+"");
		
		
		dao.board_insert(vo);
		return "redirect:list.no";
	}
	
	
	// 공지사항 신규 글 작성 화면 요청
	@RequestMapping ("/new.no")
	public String board() {
		return "notice/new";
	}
	
	
	
	// 공지사항 목록화면 요청
	@RequestMapping ("/list.no")
	public String list(HttpSession session , Model model
			,String search
			,String keyword
			, @RequestParam (defaultValue = "1") int curPage
			, @RequestParam (defaultValue = "10") int pageList
			, @RequestParam (defaultValue = "list") String viewType ) {
		
		session.setAttribute("category", "no"); // category 에 cu 를 설정
		
		// DB에서 공지사항 정보를 조회해와 목록화면에 출력
		page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
		page.setSearch(search);		// 검색 조건 값을 page에 담음
		page.setKeyword(keyword);	// 검색 키워드 값을 page에 담음
		page.setPageList(pageList);	// 페이지당 보여질 글 목록 수를 page에 담음
		page.setViewType(viewType);	// 게시판 형태를 page에 담음
		
		model.addAttribute("page", dao.board_list_notice(page));		
		return "notice/list";
	}

	// 공지글 관리화면 요청
	@RequestMapping ("/noticemanage.no")
	public String noticemanage(HttpSession session , Model model
			,String search
			,String keyword
			, @RequestParam (defaultValue = "1") int curPage
			, @RequestParam (defaultValue = "10") int pageList
			, @RequestParam (defaultValue = "list") String viewType ) {
		
		session.setAttribute("category", "no"); // category 에 cu 를 설정
		
		// DB에서 공지사항 정보를 조회해와 목록화면에 출력
		page.setCurPage(curPage);	// 현재 페이지 정보를 page에 담음
		page.setSearch(search);		// 검색 조건 값을 page에 담음
		page.setKeyword(keyword);	// 검색 키워드 값을 page에 담음
		page.setPageList(pageList);	// 페이지당 보여질 글 목록 수를 page에 담음
		page.setViewType(viewType);	// 게시판 형태를 page에 담음
		
		model.addAttribute("page", dao.board_list_notice(page));		
		return "notice/noticemanage";
	}

	
	//게시글 삭제
	@RequestMapping ("/notice_delete.bo")
	public String notice_delete(int board_id) {
		dao.board_delete(board_id);
		
		return "redirect:noticemanage.no";
	}
	
	
}



