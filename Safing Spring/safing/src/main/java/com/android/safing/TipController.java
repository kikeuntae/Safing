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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import board.BoardDAO;
import board.BoardVO;
import common.CommonService;
import common.OutPrintln;
import member.MemberVO;
import tip.YoutubeTipDAO;
import tip.YoutubeTipVO;

@Controller
public class TipController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private YoutubeTipDAO dao;
	@Autowired private BoardDAO dao_board;


	
	//tip 리스트
	@ResponseBody
	@RequestMapping("/tip_rec.home")
	public void  list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		List<YoutubeTipVO> list = dao.list();
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	
	//tip 리스트
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
		
	
}
