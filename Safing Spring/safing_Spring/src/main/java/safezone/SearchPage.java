package safezone;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;

@Component
public class SearchPage extends PageVO{
	private List<SafeZoneVO> list;
	
	
	public List<SafeZoneVO> getList() {
		return list;
	}

	public void setList(List<SafeZoneVO> list) {
		this.list = list;
	}
	

	
	
}
