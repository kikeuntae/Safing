package com.example.safing.shop.VO;

public class PurchaseHistoryVO {
    private String product_name ;
    private String package_name ;
    private int product_num     ;
    private int package_num     ;
    private int product_price 	;
    private String file_path	;
    private int order_state_num ;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public int getProduct_num() {
        return product_num;
    }

    public void setProduct_num(int product_num) {
        this.product_num = product_num;
    }

    public int getPackage_num() {
        return package_num;
    }

    public void setPackage_num(int package_num) {
        this.package_num = package_num;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public int getOrder_state_num() {
        return order_state_num;
    }

    public void setOrder_state_num(int order_state_num) {
        this.order_state_num = order_state_num;
    }
}
