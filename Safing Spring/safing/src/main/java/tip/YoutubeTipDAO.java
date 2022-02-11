package tip;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class YoutubeTipDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	
	//팁 목록
	public List<YoutubeTipVO> list() {
		return sql.selectList("tip.mapper.tiplist");
	}
	
	
	public void cnt(int id) {
		sql.update("tip.mapper.readcnt", id);
	}
		
	
	
}
