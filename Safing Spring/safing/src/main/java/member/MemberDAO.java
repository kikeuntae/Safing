package member;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MemberDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	


	//회원가입 
	public boolean insert(MemberVO vo) {
		return sql.insert("member.mapper.insert", vo) == 1 ? true : false;
	}
	
	//회원 정보 목록
	public List<MemberVO> list(MemberVO vo) {
		return sql.selectList("member.mapper.login", vo);
	}
	
	//회원 로그인, 상세정보
	public MemberVO login(MemberVO vo) {
		return sql.selectOne("member.mapper.login", vo);
	}
	
	//회원 정보수정
	public boolean update(MemberVO vo) {
		return sql.update("member.mapper.update", vo) == 1 ? true : false;
	}
	
	//회원 삭제
	public boolean delete(MemberVO vo) {
		return sql.delete("member.mapper.delete", vo) == 1 ? true : false;
	}

	//회원 가입
	public void member_insert(MemberVO vo) {
		sql.insert("member.mapper.insert", vo);
	}

	//아이디 중복확인
	public List<MemberVO> idCheck() {
		return sql.selectList("member.mapper.idCheck");
	}
	
	//아이디 찾기
	public MemberVO find_id(String phone) {
		return sql.selectOne("member.mapper.find_id", phone);
	}
	
	//비밀번호 찾기
		public MemberVO find_pw(String phone) {
			return sql.selectOne("member.mapper.find_pw", phone);
		}
	
	
	//회원 프로필사진 가져오기
	public MemberVO memberimg(MemberVO vo) {
		// TODO Auto-generated method stub
		return sql.selectOne("member.mapper.selectimg", vo);
	}

	//관리자 (회원 지우기)
	public void customer_delete(String member_id) {
		// TODO Auto-generated method stub
		sql.delete("member.mapper.delete", member_id);
	}
	
	//관리자 (회원 리스트 확인)
	public List<MemberVO> customer_list() {
		// TODO Auto-generated method stub
		return sql.selectList("member.mapper.list_customer");
	}

	//관리자 (회원 상세 확인)
	public MemberVO cusotomer_detail(String member_id) {
		// TODO Auto-generated method stub
		return sql.selectOne("member.mapper.detail_customer", member_id);
	}

	//관리자 (회원 추가)
	public void customer_insert(MemberVO vo) {
		// TODO Auto-generated method stub
		sql.insert("member.mapper.insert_customer", vo);
	}

	//관리자 (회원정보 수정)
	public void customer_update(MemberVO vo) {
		// TODO Auto-generated method stub
		sql.update("member.mapper.update_cusotomer", vo);
	}

	//프로필이미지 업데이트
	public int img_update(String string, MemberVO vo) {
		// TODO Auto-generated method stub
		return sql.update("member.mapper.update_img", vo);
	}
	
	
}
