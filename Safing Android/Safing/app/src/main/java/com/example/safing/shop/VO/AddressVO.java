package com.example.safing.shop.VO;

public class AddressVO {
    private int		addr_num     	;
    private int		addr_post     	;
    private String	addr_basic    	;
    private String	addr_detail     ;
    private String	addr_setting    ;
    private String	receiver_name   ;
    private String	receiver_phone  ;

    public int getAddr_num() {
        return addr_num;
    }
    public void setAddr_num(int addr_num) {
        this.addr_num = addr_num;
    }
    public int getAddr_post() {
        return addr_post;
    }
    public void setAddr_post(int addr_post) {
        this.addr_post = addr_post;
    }
    public String getAddr_basic() {
        return addr_basic;
    }
    public void setAddr_basic(String addr_basic) {
        this.addr_basic = addr_basic;
    }
    public String getAddr_detail() {
        return addr_detail;
    }
    public void setAddr_detail(String addr_detail) {
        this.addr_detail = addr_detail;
    }
    public String getAddr_setting() {
        return addr_setting;
    }
    public void setAddr_setting(String addr_setting) {
        this.addr_setting = addr_setting;
    }
    public String getReceiver_name() {
        return receiver_name;
    }
    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }
    public String getReceiver_phone() {
        return receiver_phone;
    }
    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

}
