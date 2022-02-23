package com.android.safing;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.CommonService;
import common.OutPrintln;
import member.MemberDAO;
import member.MemberVO;

// 고객관리 페이지처리 @Controller 생성
@Controller
public class CustomerController {
	
	@Autowired private CommonService service;
	@Autowired private OutPrintln outprintln;
	@Autowired private MemberDAO dao;

	// 고객 정보 삭제 처리 요청
	@RequestMapping("/delete.cu")
	public String delete (String id) {
		// 선택한 고객 정보를 DB에서 삭제한 후 목록 화면으로 연결
		dao.customer_delete(id);
		// 삭제 후 갱신된 목록 처리
		return "redirect:list.cu";
	}
	
	// 고객 수정 정보 저장
	@RequestMapping ("/update.cu")
	public String update(MemberVO vo,String id) {
		// 화면에서 입력한 수정 정보를 DB에 저장한 후 상세화면으로 연결
		vo.setMember_id(id);
		dao.customer_update(vo);
		// 수정 이후 해당 id의 수정된 상세정보 화면 요청
		return "redirect:detail.cu?id=" + vo.getMember_id();
	}
	
	// 고객정보 수정화면 요청
	@RequestMapping ("/modify.cu")
	public String modify(String id, Model model) {
		// 화면의 해당 고객정보를 조회해와 수정화면에 출력
		model.addAttribute("vo", dao.cusotomer_detail(id));
		return "member/modify";
	}
	
	// 신규 고객 등록
	@RequestMapping ("/insert.cu")
	public String customer_insert(MemberVO vo) {
		// 화면에서 입력한 정보를 DB에 저장한 후 목록화면으로 연결
		dao.customer_insert(vo);
		return "redirect:list.cu";
		// 등록 이후 전체 회원 목록 조회를 통해 갱신 필요
	}
	
	
	// 신규 고객 등록 화면 요청
	@RequestMapping ("/new.cu")
	public String new_customer() {
		return "member/new";
	}
	
	// 고객 상세 정보 화면 요청
	@RequestMapping ("/detail.cu")
	public String detail(String id, Model model) {
		// 선택한 고객 정보를 DB에서 조회
		model.addAttribute("vo", dao.cusotomer_detail(id));
		return "member/detail";
	}
	
	// 고객 관리 목록
	@RequestMapping ("/list.cu")
	public String list(HttpSession session, Model model) {
		
		session.setAttribute("category", "cu"); // category 에 cu 를 설정
		List<MemberVO> list = dao.customer_list();
	
		model.addAttribute("list", list);
		
		return "member/list";
	}
}
