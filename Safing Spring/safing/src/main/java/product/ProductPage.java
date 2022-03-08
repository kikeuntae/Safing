package product;

import java.util.List;

import common.PageVO;

public class ProductPage extends PageVO {
	private List<ProductManageVO> list;

	public List<ProductManageVO> getList() {
		return list;
	}

	public void setList(List<ProductManageVO> list) {
		this.list = list;
	}
	
	
}
