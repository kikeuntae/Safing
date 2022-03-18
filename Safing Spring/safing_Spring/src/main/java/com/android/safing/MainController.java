package com.android.safing;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import common.CommonService;
import common.OutPrintln;
import member.MemberDAO;
import member.MemberVO;
import product.ProductManageVO;
import safezone.SafeZoneDAO;
import safezone.SearchPage;
import tip.YoutubeTipDAO;

@Controller
public class MainController {
	Gson gson = new Gson();

	@Autowired private SafeZoneDAO dao;
	@Autowired private MemberDAO memberDao;
	@Autowired private YoutubeTipDAO dao1;

	//첫 로그인 화면
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String home(Locale locale, Model model) {
		return "login/login";	
	}
	
	//로그인
	@ResponseBody
	@RequestMapping(value = "/login.lo", method = RequestMethod.POST)
	public boolean member_login(MemberVO vo, HttpSession session) {
		boolean Result = false;
		
		vo = memberDao.login(vo);
		if(vo != null) {
			session.setAttribute("loginInfo", vo);
			Result = true;
		}
		
		return Result;
	}
	
	// 로그아웃 처리 요청
	@RequestMapping ("/logout.lo")
	public String logout(HttpSession session) {
		session.removeAttribute("loginInfo");
		return "redirect:/"; // 로그아웃시 루트(home.jsp)로 이동
	}
		
	// 회원가입화면 요청
	@RequestMapping ("/join.lo")
	public String join(HttpSession session) {
		session.setAttribute("category", "join");
	
		return "login/join";
	}
	
	// 회원가입하기
	@ResponseBody
	@RequestMapping(value = "/join_member.lo", method = RequestMethod.POST)
	public boolean join_member(MemberVO vo, HttpSession session) {
		boolean Result = false;
		
		int cnt = memberDao.join_member(vo);
		if(cnt > 0) {
			Result = true;
		}
		
		return Result;
	}
	
	//아이디 중복확인
	@ResponseBody
	@RequestMapping(value = "/id_check.lo", method = RequestMethod.POST)
	public boolean id_check(String id, HttpSession session) {
		boolean Result = false;
		
		int cnt = memberDao.id_check(id);
		if(cnt == 0) {
			Result = true;
		}
		
		return Result;
	}
	
	// 로그인화면 요청
	@RequestMapping ("/login.lo")
	public String login() {
	
		return "login/login";
	}
			
	// main화면 요청
	@RequestMapping ("/home.ma")
	public String home(Model  model, HttpSession session) {
		session.setAttribute("category", "ma");
		
		
		model.addAttribute("loginInfo");
		model.addAttribute("list", dao.list());
		model.addAttribute("tip_list", dao1.tip_list());
		
		
		return "main/home";
	}
	

	
	
	
	
	
	
}
