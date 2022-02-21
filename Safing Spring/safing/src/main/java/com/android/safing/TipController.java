package com.android.safing;

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
import tip.YoutubeTipDAO;
import tip.YoutubeTipVO;

@Controller
public class TipController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private YoutubeTipDAO dao;

	
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
		
	
}
