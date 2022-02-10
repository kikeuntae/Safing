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

	//회원 프로필사진 가져오기
	public MemberVO memberimg(MemberVO vo) {
		// TODO Auto-generated method stub
		return sql.selectOne("member.mapper.selectimg", vo);
	}
	
	
}
