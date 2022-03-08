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
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
=======
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b

import com.google.gson.Gson;

import board.BoardDAO;
<<<<<<< HEAD
import board.BoardPage;
import common.CommonService;
import common.OutPrintln;
import member.MemberDAO;
=======
import board.BoardVO;
import common.CommonService;
import common.OutPrintln;
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
import member.MemberVO;
import tip.YoutubeTipDAO;
import tip.YoutubeTipVO;

@Controller
public class TipController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private YoutubeTipDAO dao;
<<<<<<< HEAD
	@Autowired private BoardDAO board_dao;
	@Autowired private BoardPage page;

	//===============================================안드===============================================//
=======
	@Autowired private BoardDAO dao_board;


	
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
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
	
<<<<<<< HEAD
	//tip 조회수 증가
=======
	//tip 리스트
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
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
<<<<<<< HEAD
		//===============================================안드===============================================//		
	
		
		
		
		//===============================================웹===============================================//	
		

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
					vo  = board_dao.login(vo);
					session.setAttribute("loginInfo", vo);
					
					
					model.addAttribute("page", dao.board_list_youtube(page));		
					return "youtube/list";
				}	


				/*
				 * // 유튜브 상세화면 요청
				 * 
				 * @RequestMapping ("/detail.yu") public String detail_yu(int id , Model model)
				 * {
				 * 
				 * // 상세화면 요청 전 조회수 증가 dao.board_read(id);
				 * 
				 * // 해당 게시판 글을 DB에서 조회해와 상세화면에 출력 model.addAttribute("vo", dao.board_detail(id)
				 * ) ; model.addAttribute("crlf", "\r\n"); model.addAttribute("page", page);
				 * return "youtube/detail"; }
				 * 
				 * 
				 * 
				 * // 유튜브 글 삭제처리 요청
				 * 
				 * @RequestMapping("/delete.yu") public String delete_yu(int id, HttpSession
				 * session, Model model) { // 첨부 파일이 있는 글에 대해서는 해당 파일을 서버의 물리적 영역에서 삭제 BoardVO
				 * vo = dao.board_detail(id); if ( vo.getFile_path() != null) { File file = new
				 * File( session.getServletContext().getRealPath("resources") + "/" +
				 * vo.getFile_path() ); if ( file.exists() ) file.delete(); }
				 * 
				 * // 해당 게시판 글을 DB에서 삭제한 후 목록화면으로 연결 dao.board_delete(id); // return
				 * "redirect:list.bo"; model.addAttribute("uri", "list.yu");
				 * model.addAttribute("page", page); return "board/redirect"; }
				 * 
				 * // 유튜브 추가 화면 요청
				 * 
				 * @RequestMapping ("/modify.yu") public String yutub_new(int id, Model model) {
				 * YoutubeTipVO vo = new YoutubeTipVO(); vo = dao.youtube_detail(id);
				 * if(vo==null) { vo= new YoutubeTipVO(); vo.setBoard_id(id); }
				 * 
				 * model.addAttribute("vo", vo) ; return "youtube/modify_youtub"; }
				 * 
				 * // 유튜브 새로 올리기...
				 * 
				 * @RequestMapping ("/insert.yu") public String yutub_insert(BoardVO
				 * dto,YoutubeTipVO vo, MultipartFile file, HttpSession session) {
				 * 
				 * //유튜브 링크달기 위해서 board에 데이터생성 MemberVO member = (MemberVO)
				 * session.getAttribute("loginInfo");
				 * dto.setMember_id(member.getMember_id()+"");
				 * 
				 * 
				 * System.out.println(dto.getBoard_kinds());
				 * System.out.println(dto.getMember_id() );
				 * System.out.println(dto.getBoard_content() );
				 * System.out.println(dto.getBoard_title());
				 * 
				 * dao.board_insert(dto);
				 * 
				 * //만들어진 board_id값 가져와서 유튜브 테이블에 생성
				 * 
				 * 
				 * vo.setBoard_id(dao.board_new()); dao.board_insert_youtube(vo); return
				 * "redirect:list.yu"; } // 유튜브 새로 올리기 화면요청
				 * 
				 * @RequestMapping ("/new.yu") public String yutub_new(YoutubeTipVO vo,
				 * MultipartFile file, HttpSession session) {
				 * 
				 * return "youtube/new_youtube"; }
				 * 
				 * 
				 * // 유튜브 업데이트처리
				 * 
				 * @RequestMapping ("/update.yu") public String yutub_insert(YoutubeTipVO vo,
				 * MultipartFile file, HttpSession session, Model model) {
				 * 
				 * model.addAttribute("uri", "detail.yu"); model.addAttribute("id",
				 * vo.getBoard_id()); dao.board_update_youtube(vo); return "youtube/redirect"; }
				 */
		
		//===============================================웹===============================================//
=======
		
		// 유튜브 추가 화면 요청
		@RequestMapping ("/modify.yu")
		public String yutub_new(int id, Model model) {
			YoutubeTipVO vo = new YoutubeTipVO();
			vo = dao.youtube_detail(id);
			if(vo==null) {
				vo= new YoutubeTipVO();
				vo.setBoard_id(id);
			}
			
			model.addAttribute("vo", vo) ;
			return "youtube/modify_youtub";
		}
		
		// 유튜브 새로 올리기...
		@RequestMapping ("/insert.yu")
		public String yutub_insert(BoardVO dto,YoutubeTipVO vo, MultipartFile file, HttpSession session) {
			
			//유튜브 링크달기 위해서 board에 데이터생성
			MemberVO member = (MemberVO) session.getAttribute("loginInfo");
			dto.setMember_id(member.getMember_id()+"");
			
			
			System.out.println(dto.getBoard_kinds());
			System.out.println(dto.getMember_id() );
			System.out.println(dto.getBoard_content() );
			System.out.println(dto.getBoard_title());
			
			dao_board.board_insert(dto);
			
			//만들어진 board_id값 가져와서 유튜브 테이블에 생성
			
		
			vo.setBoard_id(dao_board.board_new());
			dao.board_insert_youtube(vo);
			return "redirect:list.yu";
		}
		// 유튜브 새로 올리기 화면요청
		@RequestMapping ("/new.yu")
		public String yutub_new(YoutubeTipVO vo, MultipartFile file, HttpSession session) {
			
			return "youtube/new_youtube";
		}
		
		
		// 유튜브 업데이트처리
		@RequestMapping ("/update.yu")
		public String yutub_insert(YoutubeTipVO vo, MultipartFile file, HttpSession session, Model model) {

			model.addAttribute("uri", "detail.yu");
			model.addAttribute("id", vo.getBoard_id());
			dao.board_update_youtube(vo);
			return "youtube/redirect";
		}
		
	
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
}
