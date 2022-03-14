package com.android.safing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

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

		String strVo = req.getParameter("vo");	
		MemberVO vo = gson.fromJson(strVo, MemberVO.class);
		
		
		vo = dao.memberimg(vo);
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		
		
		PrintWriter writer = res.getWriter();
		writer.println( gson.toJson(vo));
		
	}
	
	@ResponseBody
	@RequestMapping("/memberimg_up.me")
	public void test(HttpServletRequest req , HttpServletResponse res ,HttpSession session) throws IOException {
		String tempVo = req.getParameter("vo");
		MemberVO vo = gson.fromJson(tempVo, MemberVO.class);
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		int result = 0;
		MultipartRequest mulReq = (MultipartRequest) req;
		MultipartFile file = mulReq.getFile("file");
		if(file != null) {
			System.out.println("Null 아님 파일 들어옴");
			String path = service.fileupload("member", file, session);
			String server_path = "http://" + req.getLocalAddr()
			+ ":" + req.getLocalPort() + req.getContextPath()+"/resources/";
			System.out.println(server_path + path);
			vo.setMember_filepath(server_path + path);
			result = dao.img_update("member.mapper.insert", vo);
		}else {
			System.out.println("Null임 파일 안들어옴..");
		
		}
		writer.print(result);
	}

	
	//회원가입
	@ResponseBody
	@RequestMapping("/join.me")
	public void join(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws Exception {
		PrintWriter writer = outprintln.outprintln(req, res);
		String strVo = req.getParameter("vo");
		MemberVO vo = gson.fromJson(strVo,MemberVO.class);
		System.out.println(vo.getMember_id());
		dao.member_insert(vo);
		

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
		String strVo1 = req.getParameter("vo");
		MemberVO vo = gson.fromJson(strVo1,MemberVO.class);
		
		MemberVO vo1 = dao.login(vo); 
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		writer.println( gson.toJson(vo1));
		
		
	}
	
	//회원 정보수정
	   @ResponseBody
	   @RequestMapping("/update.me")
	   public void  update(HttpServletRequest req, HttpServletResponse res) throws Exception{
	      PrintWriter writer = outprintln.outprintln(req, res);
	      String strVo = req.getParameter("vo");
	      MemberVO vo = gson.fromJson(strVo,MemberVO.class);
	      System.out.println(vo.getMember_id());
	      dao.update(vo);
	      
	      
	   }
	
	//회원삭제
	@ResponseBody
	@RequestMapping("/delete.me")
	public void  delete(HttpServletRequest req, HttpServletResponse res) throws Exception{
		PrintWriter writer = outprintln.outprintln(req, res);
	}
	
	@ResponseBody
	@RequestMapping("sendsms.me")
	public void sendsms(HttpServletRequest req, HttpServletResponse res) throws Exception {
		final String apiKey = "NCS4KWU69INZ4QNO";
		final String apiSecret = "XRD59PZLRZOK4SCP95OAK5ZD5JGHCCUS";
		//final 상수로 쓰는 이유는 api키가 수정되면 사용못하게 되는것을 막기위함
	//문자를 보내기위해서는 coolsms서버 이용함
		//CoolSMS 서버에서 요구하는 param를 보내주기만 하면
		//문자전송은 알아서함. (HashMap)<=중복된 값을 넣을수없음.
		PrintWriter writer = outprintln.outprintln(req, res);
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		String random = UUID.randomUUID().toString().substring(0,4);
//		Message coolsms = new Message(apiKey, apiSecret);
//		HashMap<String, String> params = new HashMap<>();
//		params.put("to", "01045478710");
//		params.put("from", "01045478710");
//		params.put("type", "SMS");  //type(SMS,LMS,MMS,AT)
//		params.put("text", random + " 인증번호를 입력하세요.");
//		params.put("app_version", "JAVA SDK v1.2");
//		try {
//		JSONObject obj = coolsms.send(params);
//		System.out.println(obj.toString());
//		} catch (CoolsmsException e) {
//			System.out.println(e.getMessage());
//			System.out.println(e.getCode());
//		}

		writer.println(random);
		
	}
	
	//아이디 중복확인
	@ResponseBody
	@RequestMapping("idCheck.me")
	public void idCheck(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<MemberVO> list = dao.idCheck();
		
		PrintWriter writer = outprintln.outprintln(req, res);
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		writer.println( gson.toJson(list));
		
	}
	
	//아이디 찾기
	@ResponseBody
	@RequestMapping("find_id.me")
	public void find_id(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String phone = req.getParameter("phone");
		
		MemberVO vo= dao.find_id(phone);
		
		PrintWriter writer = outprintln.outprintln(req, res);
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		writer.println( gson.toJson(vo));
		
	}
	
	//비밀번호 찾기
		@ResponseBody
		@RequestMapping("find_pw.me")
		public void find_pw(HttpServletRequest req, HttpServletResponse res) throws Exception {
			String phone = req.getParameter("phone");
			
			MemberVO vo= dao.find_pw(phone);
			
			PrintWriter writer = outprintln.outprintln(req, res);
			req.setCharacterEncoding("UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html");
			writer.println( gson.toJson(vo));
			
		}
}
