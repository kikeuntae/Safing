package com.android.safing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import common.CommonService;
import common.OutPrintln;
import safezone.SafeZoneDAO;
import safezone.SafeZoneVO;

@Controller
public class SafeZoneController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private SafeZoneDAO dao;
	

	
	//safezone 가입 list
	@ResponseBody
	@RequestMapping("/sfzone_rec.home")
	public void  list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		List<SafeZoneVO> list = dao.list();
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	
	// 캠핑장 검색
	@ResponseBody
	@RequestMapping ("/all_list.home")
	public void all_list(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//name, email, phone 어떤 검색어를 넣어도
		String search = req.getParameter("search");
		List<SafeZoneVO> list = dao.all_list(search);
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println(gson.toJson(list));
	}
	
	
}
