package tip;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import board.BoardPage;
import board.BoardVO;

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
	
	
	//게시판(유튜브) 리스트 출력
	public BoardPage board_list_youtube(BoardPage page) {
		// 전체 게시글 수 조회
		page.setTotalList(sql.selectOne("tip.mapper.totalList_yu", page));
		// 페이징 처리된 전체 게시글 목록 조회
		List<BoardVO> list = sql.selectList("tip.mapper.list_yu", page) ;
		page.setList(list );
		
		return page;
	}

	//글에 유튜브 업로드
	public void insert(YoutubeTipVO vo) {

		sql.insert("tip.mapper.insert", vo);
	}
	
	// 유튜브 상세화면 요청
	public YoutubeTipVO detail(int id) {
		return sql.selectOne("tip.mapper.detail", id);
	}
	
	
	//수정을 위한 유튜브  상세정보요청
	public YoutubeTipVO youtube_detail(int board_id) {

		return  sql.selectOne("tip.mapper.youtube_detail", board_id);
	}

	// 유튜브 수정 요청
	
	public int update(YoutubeTipVO vo) {
		return sql.update("tip.mapper.update", vo);
	}

	// 유튜브 삭제 요청
	public int delete(int id) {
		return sql.delete("tip.mapper.delete", id);
	}
		
	
	
}
