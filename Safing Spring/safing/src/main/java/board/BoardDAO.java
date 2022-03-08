package board;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import member.MemberVO;
import tip.YoutubeTipVO;

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
		if(!(vo.getFile_path().equals(null))) {
		sql.update("board_update_img", vo);
		}
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
		vo.setBoard_id(sql.selectOne("board.mapper.board_new"));
		sql.insert("board.mapper.board_insert_img", vo);
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
	public List<Board_CommentVO> comment_list(int id) {
		// TODO Auto-generated method stub
		return sql.selectList("board.mapper.comment_list", id);
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

	public BoardPage board_list(BoardPage page) {
		// 전체 게시글 수 조회
		page.setTotalList(sql.selectOne("board.mapper.totalList", page));
		// 페이징 처리된 전체 게시글 목록 조회
		page.setList( sql.selectList("board.mapper.list", page) );
		return page;
	}
	

	public int board_insert(BoardVO vo) {		
		return sql.insert("board.mapper.board_insert", vo);
	}


	public BoardVO board_detail(int id) {
		return sql.selectOne("board.mapper.detail", id);
	}

	
	public int board_read(int id) {
		return sql.update("board.mapper.read", id);
	}

	//게시판 글 수정
	public int board_update(BoardVO vo) {
		return sql.update("board.mapper.board_update", vo);
	}


	public int board_delete(int id) {
		return sql.delete("board.mapper.delete", id);
	}


	public int board_comment_insert(Board_CommentVO vo) {
		return sql.insert("board.mapper.comment_insert", vo);
	}

	
	public int board_comment_update(Board_CommentVO vo) {
		return sql.update("board.mapper.comment_update", vo);
	}

	public int board_comment_delete(int id) {
		return sql.delete("board.mapper.comment_delete", id);
	}

	
	public List<Board_CommentVO> board_comment_list(int id) {
		return sql.selectList("board.mapper.board_comment_list", id);
	}

	//임시로그인
	public MemberVO login(MemberVO vo) {
		// TODO Auto-generated method stub
		return sql.selectOne("board.mapper.login", vo);
		
	}

	//글쓰기 이미지 파일 저장
	public void board_insert_img(BoardVO vo) {
		// TODO Auto-generated method stub
		sql.insert("board.mapper.board_insert_img", vo);
	}

	//게시판의 사진 찾기
	public Board_FileVO board_file_select(int id) {
		// TODO Auto-generated method stub
		return sql.selectOne("board.mapper.board_select_img", id);

	}

	//게시판 이미지 수정
	public void board_update_img(Board_FileVO vo) {
		// TODO Auto-generated method stub
		sql.update("board.mapper.board_update_img", vo);
	}
	

	//게시판(공지사항) 리스트 출력
	public BoardPage board_list_notice(BoardPage page) {
		// 전체 게시글 수 조회
		page.setTotalList(sql.selectOne("board.mapper.totalList_no", page));
		// 페이징 처리된 전체 게시글 목록 조회
		page.setList( sql.selectList("board.mapper.list_no", page) );
		return page;
	}
	//게시판(유튜브) 리스트 출력
	public BoardPage board_list_youtube(BoardPage page) {
		// 전체 게시글 수 조회
		page.setTotalList(sql.selectOne("board.mapper.totalList_yu", page));
		// 페이징 처리된 전체 게시글 목록 조회
		page.setList( sql.selectList("board.mapper.list_yu", page) );
		
		return page;
	}

	//방금만든 board_id값 조회하기
	public int board_new() {
		// TODO Auto-generated method stub
		return sql.selectOne("board.mapper.board_new");
	}

	//동영상 최신리스트 순서
	public List<Board_MovieDTO> movielist_new() {
		// TODO Auto-generated method stub
		return sql.selectList("board.mapper.movielist_new");
	}

	//동영상 마이페이지 리스트
	public List<Board_MovieDTO> movielist_mypage(MemberVO vo) {
		// TODO Auto-generated method stub
		return sql.selectList("board.mapper.movielist_mypage",vo);
	}
	
}
