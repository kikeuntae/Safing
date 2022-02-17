package common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import board.Board_FileVO;
import member.MemberVO;

@Service
public class InsertDAO {
	
	@Autowired @Qualifier("bteam") private SqlSession sql;
	
	//이미지등록
	public boolean img_insert(ImginsertVO vo){
		return sql.update("insert.mapper.insert_img", vo)== 1 ? true : false;
		
	}
	
	//태그등록
	public boolean tag_insert(TagVO vo){
		return sql.update("insert.mapper.insert_tag", vo)== 1 ? true : false;
	}
		
	//상품등록
	public boolean pro_insert(ProductinVO vo){
		return sql.update("insert.mapper.insert_pro", vo)== 1 ? true : false;
		
	}
	
	//상품이미지등록
	public boolean profile_insert(Product_FileVO vo){
		return sql.update("insert.mapper.insert_profile", vo)== 1 ? true : false;
		
	}
	
	//회원등록
	public boolean member_insert(MemberVO vo){
		return sql.update("insert.mapper.member_insert", vo)== 1 ? true : false;
		
	}
	
	//동영상등록
		public boolean insert_boardfile(Board_FileVO vo){
			return sql.update("insert.mapper.insert_boardfile", vo)== 1 ? true : false;		
	}
		

	//패키지 리스트
	public List<ThemeRecDTO> Theme_Pager_list(){
		return sql.selectList("insert.mapper.Theme_Pager_list");
	}
			
		
}
