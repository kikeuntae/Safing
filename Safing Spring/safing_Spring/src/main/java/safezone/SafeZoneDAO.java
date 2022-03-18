package safezone;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SafeZoneDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	
	//SafeZone 가입 목록
	public List<SafeZoneVO> list() {
		return sql.selectList("sfzone.mapper.sfzonelist");
	}


	public List<SafeZoneVO> all_list(String search) {

		return sql.selectList("sfzone.mapper.all_list", search);
	}
	
	
	public SearchPage search_list(SearchPage page) {
		// 전체 게시글 수 조회
		page.setTotalList(sql.selectOne("sfzone.mapper.totalList", page));
		// 페이징 처리된 전체 게시글 목록 조회
		List<SafeZoneVO> list = sql.selectList("sfzone.mapper.search_list", page);
		page.setList( list);
		
		return page;
	}


	public SafeZoneVO campinfo_detail(int contentid) {
		
		return sql.selectOne("sfzone.mapper.detail", contentid);
	}
	

	
}
