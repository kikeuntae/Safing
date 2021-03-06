package campinfo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CampImgDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	
	
	//캠핑장 상세 이미지 목록
	public List<CampImgVO> list(int contentid) {
		return sql.selectList("campimg_list.mapper.campimglist", contentid);
	}
	
	//캠핑장 상세 이미지 목록
	public List<CampImgVO> img_list(int contentid) {
		return sql.selectList("campimg_list.mapper.wcampimglist", contentid);
	}
	
	
	
	
}
