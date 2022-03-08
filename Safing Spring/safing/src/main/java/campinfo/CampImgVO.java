package campinfo;

import java.io.Serializable;

public class CampImgVO implements Serializable {

<<<<<<< HEAD
    String  imageurl, imageid;
    int contentid;
    
    public int getContentid() {
        return contentid;
    }

    public void setContentid(int contentid) {
=======
    String contentid, imageurl, imageid;

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
        this.contentid = contentid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }
}
