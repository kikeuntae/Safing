package tip;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import board.BoardPage;
<<<<<<< HEAD
import board.BoardVO;
=======
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b

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
	
	
<<<<<<< HEAD
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
	public void board_insert_youtube(YoutubeTipVO vo) {

=======


	//글에 유튜브 업로드
	public void board_insert_youtube(YoutubeTipVO vo) {
		// TODO Auto-generated method stub
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
		sql.insert("tip.mapper.insert_youtube", vo);
	}
	//수정을 위한 유튜브  상세정보요청
	public YoutubeTipVO youtube_detail(int id) {
<<<<<<< HEAD

=======
		// TODO Auto-generated method stub
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
		return  sql.selectOne("tip.mapper.youtube_detail", id);
	}

	public void board_update_youtube(YoutubeTipVO vo) {
<<<<<<< HEAD

=======
		// TODO Auto-generated method stub
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
		sql.insert("tip.mapper.update_youtube", vo);
	}
		
	
	
}
