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

import board.Board_MovieDTO;
import common.CommonService;
import common.OutPrintln;
import member.MemberDAO;
import member.MemberVO;

@Controller
public class MemberController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private MemberDAO dao;

	//회원 프로필사진 가져오기
	@ResponseBody
	@RequestMapping("/memberimg.me")
	public void img(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws Exception {

		String strVo = req.getParameter("member_id");	
		MemberVO member = new MemberVO();
		member.setMember_id(strVo);
		
		MemberVO vo = dao.memberimg(member);
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		
		
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(vo));
		
	}

	
	//회원가입
	@ResponseBody
	@RequestMapping("/join.me")
	public void join(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws Exception {
		PrintWriter writer = outprintln.outprintln(req, res);

	}

	//회원 정보 목록
	@ResponseBody
	@RequestMapping("/list.me")
	public void  list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//회원 로그인, 상세정보
	@ResponseBody
	@RequestMapping("/login.me")
	public void  login(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//회원 정보수정
	@ResponseBody
	@RequestMapping("/update.me")
	public void  update(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	//회원삭제
	@ResponseBody
	@RequestMapping("/delete.me")
	public void  delete(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
}
