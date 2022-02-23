package theme;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ThemeDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	
	//패키지 리스트
	public List<ThemeRecDTO> Theme_Pager_list(){
		return sql.selectList("theme.mapper.Theme_Pager_list");
	}
	
	

	
	
}
