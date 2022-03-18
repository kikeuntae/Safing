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
import theme.ThemeDAO;
import theme.ThemeRecDTO;

@Controller
public class ThemeController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private ThemeDAO dao;

	
	@ResponseBody
	@RequestMapping("/Theme_Pager.home")
	public void  Theme_Pager_list(HttpServletRequest req, HttpServletResponse res) throws Exception{
		List<ThemeRecDTO> list = dao.Theme_Pager_list();
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(list));
	}
	
	
}
