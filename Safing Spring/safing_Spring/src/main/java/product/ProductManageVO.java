package product;

import java.util.List;

public class ProductManageVO {
	private int		no   ;	
	private int		product_num   ;	
	private String 	product_name  ;	
	private int		product_price;	
	private int		product_stock ;	
	private String 	product_date  ;	
	private String 	product_kind  ;
	private List<String> imagelist;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public List<String> getImagelist() {
		return imagelist;
	}
	public void setImagelist(List<String> imagelist) {
		this.imagelist = imagelist;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	public String getProduct_date() {
		return product_date;
	}
	public void setProduct_date(String product_date) {
		this.product_date = product_date;
	}
	public String getProduct_kind() {
		return product_kind;
	}
	public void setProduct_kind(String product_kind) {
		this.product_kind = product_kind;
	} 
	
	
	
}
