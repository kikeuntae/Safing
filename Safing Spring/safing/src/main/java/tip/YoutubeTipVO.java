package tip;

public class YoutubeTipVO {
<<<<<<< HEAD
	int   id, youtubecnt, board_id;
=======
	int  id, youtubecnt, board_id;
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
    String thumbnails, youtubetitle, youtubecontent, play;

	
	 public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getId() { return id; }
	 
	 public void setId(int id) { this.id = id; }
	 
	 public int getYoutubecnt() { return youtubecnt; }
	 
	 public void setYoutubecnt(int youtubecnt) { this.youtubecnt = youtubecnt; }
	 

	 

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getYoutubetitle() {
        return youtubetitle;
    }

    public void setYoutubetitle(String youtubetitle) {
        this.youtubetitle = youtubetitle;
    }

    public String getYoutubecontent() {
        return youtubecontent;
    }

    public void setYoutubecontent(String youtubecontent) {
        this.youtubecontent = youtubecontent;
    }

	
	public String getPlay() { return play; }
	  
	public void setPlay(String play) { this.play = play; }
	
}
