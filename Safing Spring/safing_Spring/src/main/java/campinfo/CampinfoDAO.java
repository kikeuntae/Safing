package campinfo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CampinfoDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	//캠핑 등록 
	public boolean insert(CampinfoVO vo) {
		return sql.insert("camping.mapper.insert", vo) == 1 ? true : false;
	}
	
	//캠핑 정보 목록
	public List<CampinfoVO> list(CampinfoVO vo) {
		return sql.selectList("camping.mapper.list", vo);
	}
	
	//캠핑 상세정보
	public CampinfoVO detail(CampinfoVO vo) {
		return sql.selectOne("camping.mapper.detail", vo);
	}
	
	//캠핑 정보수정
	public boolean update(CampinfoVO vo) {
		return sql.update("camping.mapper.update", vo) == 1 ? true : false;
	}
	
	//캠핑 삭제
	public boolean delete(CampinfoVO vo) {
		return sql.delete("camping.mapper.delete", vo) == 1 ? true : false;
	}
	
	
}
