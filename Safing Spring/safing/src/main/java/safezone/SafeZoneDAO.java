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
	
	
	

	
}
