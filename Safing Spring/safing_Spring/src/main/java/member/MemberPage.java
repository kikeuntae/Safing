package member;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;


@Component
public class MemberPage extends PageVO{

	private List<MemberVO> list;

	public List<MemberVO> getList() {
		return list;
	}

	public void setList(List<MemberVO> list) {
		this.list = list;
	}

	
	
}
