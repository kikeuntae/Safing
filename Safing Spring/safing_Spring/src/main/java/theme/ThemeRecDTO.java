package theme;

import java.io.Serializable;

public class ThemeRecDTO  implements Serializable {
	    private int Package_num;
	    private String package_name;
	    private String facltnm ;
	    private String tag_key ;
	    private String firstimageurl;
	    private String file_path1;
	    private String file_path2;

	    public int getPackage_num() {
	        return Package_num;
	    }
	    public void setPackage_num(int package_num) {
	        Package_num = package_num;
	    }
	    public String getPackage_name() {
	        return package_name;
	    }
	    public void setPackage_name(String package_name) {
	        this.package_name = package_name;
	    }
	    public String getFacltnm() {
	        return facltnm;
	    }
	    public void setFacltnm(String facltnm) {
	        this.facltnm = facltnm;
	    }
	    public String getTag_key() {
	        return tag_key;
	    }
	    public void setTag_key(String tag_key) {
	        this.tag_key = tag_key;
	    }
	    public String getFirstimageurl() {
	        return firstimageurl;
	    }
	    public void setFirstimageurl(String firstimageurl) {
	        this.firstimageurl = firstimageurl;
	    }
	    public String getFile_path1() {
	        return file_path1;
	    }
	    public void setFile_path1(String file_path1) {
	        this.file_path1 = file_path1;
	    }
	    public String getFile_path2() {
	        return file_path2;
	    }
	    public void setFile_path2(String file_path2) {
	        this.file_path2 = file_path2;
	    }
	}

