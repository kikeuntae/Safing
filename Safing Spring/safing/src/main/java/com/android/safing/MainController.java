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
	

	// 로그인화면 요청
	@RequestMapping ("/login.lo")
	public String login(Model  model) {
	
		return "login/login";
	}
	
}
