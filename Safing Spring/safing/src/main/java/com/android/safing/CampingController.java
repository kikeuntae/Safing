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

import campinfo.CampImgVO;
import common.CommonService;
import common.OutPrintln;

@Controller
public class CampingController {
	Gson gson = new Gson();
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private campinfo.CampImgDAO dao;

	
	//캠핑장 상세 사진 목록
		@ResponseBody
		@RequestMapping("/campimg_list.home")
		public void  campimg_list(HttpServletRequest req, HttpServletResponse res, int contentid) throws Exception{
			

			List<CampImgVO> list = dao.list(contentid);
			req.setCharacterEncoding("UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html");
			PrintWriter writer = res.getWriter();
			writer.println( gson.toJson(list));
		}
		
	
}
