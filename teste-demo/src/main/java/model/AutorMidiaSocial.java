package model;


public class AutorMidiaSocial {
	private int fk_autorid;
	private int fk_midiatipoid;
	private String link;
	private String tag;
	
	
	public int getAutorId() {
		return this.fk_autorid;
	}
	
	public void setAutorId(int id) {
		this.fk_autorid = id;
	}
	
	public int getMidiaSocialId() {
		return this.fk_midiatipoid;
	}
	
	public void setMidiaSocialId(int id) {
		this.fk_midiatipoid = id;
	}
	
	public String getLink() {
		return this.link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
}
