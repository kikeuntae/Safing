package com.android.safing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.CommonService;
import product.ProductDAO;

@Controller
public class ShopController {
	
	@Autowired private CommonService service;
	@Autowired private ProductDAO dao;
	
	//쇼핑 메인페이지
	@RequestMapping ("/list.shop")
	public String board(Model  model) {
		
		
		return "shop/shopmain";
	}
	
	
	//
}
