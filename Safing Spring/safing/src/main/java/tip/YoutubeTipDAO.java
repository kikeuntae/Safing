package tip;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import board.BoardPage;

@Service
public class YoutubeTipDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	
	//팁 목록
	@ResponseBody
	public List<YoutubeTipVO> tip_list() {
		return sql.selectList("tip.mapper.tiplist");
	}
	
	
	public void cnt(int id) {
		sql.update("tip.mapper.readcnt", id);
	}
	
	


	//글에 유튜브 업로드
	public void board_insert_youtube(YoutubeTipVO vo) {
		// TODO Auto-generated method stub
		sql.insert("tip.mapper.insert_youtube", vo);
	}
	//수정을 위한 유튜브  상세정보요청
	public YoutubeTipVO youtube_detail(int id) {
		// TODO Auto-generated method stub
		return  sql.selectOne("tip.mapper.youtube_detail", id);
	}

	public void board_update_youtube(YoutubeTipVO vo) {
		// TODO Auto-generated method stub
		sql.insert("tip.mapper.update_youtube", vo);
	}
		
	
	
}
