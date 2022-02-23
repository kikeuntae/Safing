package com.android.safing;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import board.BoardDAO;
import board.BoardVO;
import board.Board_CommentVO;
import board.Board_MovieDTO;
import common.CommonService;
import common.OutPrintln;
import member.MemberDAO;
import member.MemberVO;

@Controller
public class CommentController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private BoardDAO dao;

	
	//댓글 등록
	@ResponseBody
	@RequestMapping("/commentinsert.co")
	public void join(HttpServletRequest req, HttpServletResponse res, HttpSession session, MultipartFile file) throws Exception {
	PrintWriter writer = outprintln.outprintln(req, res);	
	String strVo = req.getParameter("vo");	
	Board_CommentVO vo = gson.fromJson(strVo, Board_CommentVO.class);
	
	System.out.println(vo.getBoard_id());
	System.out.println(vo.getComment_content());
	System.out.println(vo.getComment_regdate());
	System.out.println(vo.getMember_id());

	
	dao.comment_insert(vo);

	}

	//댓글정보 목록
	@ResponseBody
	@RequestMapping("/commentselect.co")
	public void  list(HttpServletRequest req, HttpServletResponse res) throws Exception{

		String strVo = req.getParameter("video_id");
		System.out.println(strVo);
		List<Board_CommentVO> list = dao.comment_list(Integer.parseInt(strVo));
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		System.out.println(list.get(0).getComment_content());
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
		
	}

	
	//댓글 수정
	@ResponseBody
	@RequestMapping("/commentupdate.co")
	public void  update(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
		String strVo = req.getParameter("vo");	
		Board_CommentVO vo = gson.fromJson(strVo, Board_CommentVO.class);
		
		dao.comment_update(vo);
		
	}
	
	//댓글 삭제
	@ResponseBody
	@RequestMapping("/commentdelete.co")
	public void  delete(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
		String strVo = req.getParameter("vo");	
		Board_CommentVO vo = gson.fromJson(strVo, Board_CommentVO.class);
		
		System.out.println("test");
		
		dao.comment_delete(vo);	
	}
	

	
	
	
	
	


	
	
	
}
