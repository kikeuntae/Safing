package safezone;

import java.io.Serializable;
import java.util.HashMap;

public class SafeZoneVO implements Serializable {
	
	private String 	contentid, facltnm, addr1, intro, resvecl, tel, homepage, induty, prmisnde, insrncat, animalcmgcl, facltdivnm,
    mangedivnm,  mgcdiv, operdecl, toiletco, swrmco, wtrplco, sbrscl, mapx, mapy, sfzone, firstimageurl ;
	private HashMap<String,String> iconList = new HashMap<String,String>();
<<<<<<< HEAD
	private int readcnt, no, next, prev;
	
	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

=======
	
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
	public void setIconList() {
		String[] iconarr = this.sbrscl.split(",");
		if(iconarr.length > 0 ) {
			for(int i = 0 ; i<iconarr.length ; i ++){
				if(iconarr[i].equals("전기")) this.iconList.put("전기" , "ico_volt.png");
				else if(iconarr[i].equals("무선인터넷"))  this.iconList.put("무선인터넷" , "ico_wifi.png");
				else if(iconarr[i].equals("장작판매"))  this.iconList.put("장작판매" , "ico_wood.png");
				else if(iconarr[i].equals("온수"))  this.iconList.put("온수" , "ico_hotwater.png");
				else if(iconarr[i].equals("트램폴린"))  this.iconList.put("트램폴린" , "ico_tramp.png");
				else if(iconarr[i].equals("물놀이장"))  this.iconList.put("물놀이장" , "ico_pool.png");
				else if(iconarr[i].equals("놀이터"))  this.iconList.put("놀이터" , "ico_playzone.png");
				else if(iconarr[i].equals("산책로"))  this.iconList.put("산책로" , "ico_walk.png");
				else if(iconarr[i].equals("운동장"))  this.iconList.put("운동장" , "ico_ground.png");
				else if(iconarr[i].equals("운동시설"))  this.iconList.put("운동시설" , "ico_sports.png");
				else if(iconarr[i].equals("마트.편의점"))  this.iconList.put("마트.편의점" , "ico_mart.png");
	
			}
		}
	}

	public HashMap<String,String> getIconList() {
		return iconList;
	}

	

<<<<<<< HEAD
=======
	private int no;
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getFacltnm() {
		return facltnm;
	}

	public void setFacltnm(String facltnm) {
		this.facltnm = facltnm;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getResvecl() {
		return resvecl;
	}

	public void setResvecl(String resvecl) {
		this.resvecl = resvecl;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getInduty() {
		return induty;
	}

	public void setInduty(String induty) {
		this.induty = induty;
	}

	public String getPrmisnde() {
		return prmisnde;
	}

	public void setPrmisnde(String prmisnde) {
		this.prmisnde = prmisnde;
	}

	public String getInsrncat() {
		return insrncat;
	}

	public void setInsrncat(String insrncat) {
		this.insrncat = insrncat;
	}

	public String getAnimalcmgcl() {
		return animalcmgcl;
	}

	public void setAnimalcmgcl(String animalcmgcl) {
		this.animalcmgcl = animalcmgcl;
	}

	public String getFacltdivnm() {
		return facltdivnm;
	}

	public void setFacltdivnm(String facltdivnm) {
		this.facltdivnm = facltdivnm;
	}

	public String getMangedivnm() {
		return mangedivnm;
	}

	public void setMangedivnm(String mangedivnm) {
		this.mangedivnm = mangedivnm;
	}

	public String getMgcdiv() {
		return mgcdiv;
	}

	public void setMgcdiv(String mgcdiv) {
		this.mgcdiv = mgcdiv;
	}

	public String getOperdecl() {
		return operdecl;
	}

	public void setOperdecl(String operdecl) {
		this.operdecl = operdecl;
	}

	public String getToiletco() {
		return toiletco;
	}

	public void setToiletco(String toiletco) {
		this.toiletco = toiletco;
	}

	public String getSwrmco() {
		return swrmco;
	}

	public void setSwrmco(String swrmco) {
		this.swrmco = swrmco;
	}

	public String getWtrplco() {
		return wtrplco;
	}

	public void setWtrplco(String wtrplco) {
		this.wtrplco = wtrplco;
	}

	public String getSbrscl() {
		return sbrscl;
	}
	
	public void setSbrscl(String sbrscl) {
		this.sbrscl = sbrscl;
		if(sbrscl != null && sbrscl.length() > 1) {
			setIconList();
		}
	
	}

	public String getMapx() {
		return mapx;
	}

	public void setMapx(String mapx) {
		this.mapx = mapx;
	}

	public String getMapy() {
		return mapy;
	}

	public void setMapy(String mapy) {
		this.mapy = mapy;
	}

	public String getSfzone() {
		return sfzone;
	}

	public void setSfzone(String sfzone) {
		this.sfzone = sfzone;
	}

	public String getFirstimageurl() {
		return firstimageurl;
	}

	public void setFirstimageurl(String firstimageurl) {
		this.firstimageurl = firstimageurl;
	}
	
	
}
