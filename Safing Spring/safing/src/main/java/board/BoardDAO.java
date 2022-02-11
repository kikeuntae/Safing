package board;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BoardDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	
	//동영상 정보 목록
	public List<Board_MovieDTO> movielist() {
		return sql.selectList("board.mapper.movielist");
	}
	
	//동영상 정보 수정 
	public void movie_update(Board_MovieDTO vo){
		sql.update("board.mapper.movieupdate", vo);
	}
	
	//동영상 정보 삭제
	public boolean movie_delete(Board_MovieDTO vo) {
		return sql.delete("board.mapper.moviedelete", vo) == 1 ? true : false;
		// TODO Auto-generated method stub
	}
	
	//동영상 정보 등록
	public void movie_create(Board_MovieDTO vo) {
		// TODO Auto-generated method stub
		sql.insert("board.mapper.moviecreate", vo);
	}

	//좋아요 처리
	public void board_like(Board_MovieDTO vo) {
		// TODO Auto-generated method stub
		sql.update("board.mapper.like",vo);
	}
	
	
	//동영상 등록에 필요한 board값 조회
	public int file_select(Board_MovieDTO vo) {
		return sql.selectOne("board.mapper.moviefile_select");
	}

	
	//댓글 등록
	public void comment_insert(Board_CommentVO vo) {
		// TODO Auto-generated method stub
		sql.insert("board.mapper.comment_insert", vo);
		
	}
	//댓글 출력
	public List<Board_CommentVO> comment_list(int video_id) {
		// TODO Auto-generated method stub
		return sql.selectList("board.mapper.comment_list", video_id);
	}

	//댓글 수정
	public void comment_update(Board_CommentVO vo) {
		// TODO Auto-generated method stub
		sql.insert("board.mapper.comment_update", vo);
	}

	//댓글 삭제
	public void comment_delete(Board_CommentVO vo) {
		// TODO Auto-generated method stub
		sql.insert("board.mapper.comment_delete", vo);
	}
	
	//동영상 댓글 수 새로고침
	public int movie_comment_cnt(int id) {
		// TODO Auto-generated method stub
		return sql.selectOne("board.mapper.movie_comment_cnt", id);
	}
	
}
