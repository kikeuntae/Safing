package com.android.safing;


import java.util.UUID;

import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.CommonService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	/*
	 * @Autowired private CommonService common;
	 * 
	 * private String naver_client_id = "jP8OUbSsgMTpkHgqkM4B";
	 * 
	 * @RequestMapping("/naverLogin") public String naverLogin(HttpSession session)
	 * { String state = UUID.randomUUID().toString();
	 * 
	 * session.setAttribute("state", state); StringBuffer url = new
	 * StringBuffer("https://nid.naver.com/oauth2.0/authorize?response_type=code");
	 * url.append("&client_id=").append(naver_client_id);
	 * url.append("&state=").append(state);
	 * url.append("&redirect_uri=http://localhost/safing/navercallback");
	 * 
	 * return "redirect:" + url.toString(); }
	 * 
	 * @RequestMapping("/navercallback") public String
	 * navercallback(@RequestParam(required = false) String code, HttpSession
	 * session, String state,
	 * 
	 * @RequestParam(required = false) String error) { if ( !
	 * state.equals(session.getAttribute("state")) || error != null ) { return
	 * "redirect:/"; }
	 * 
	 * StringBuffer url = new StringBuffer(
	 * "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
	 * url.append("&client_id=").append(naver_client_id);
	 * url.append("&client_secret=VV7lUj9G96"); url.append("&code=").append(code);
	 * url.append("&state=").append(state);
	 * 
	 * JSONObject json = new JSONObject(common.requestAPI(url)); String token =
	 * json.getString("access_token"); String type = json.getString("token_type");
	 * 
	 * url = new StringBuffer("https://openapi.naver.com/v1/nid/me"); json = new
	 * JSONObject( common.requestAPI(url, type + " " + token) );
	 * 
	 * 
	 * return "redirect:/home.ma"; }
	 */
	
}
