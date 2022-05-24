package model;


public class Tag {
	private int id;
	private int fk_id;
	private String tag;
	private int cod_tag_img_preview;
	
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getFkId() {
		return this.fk_id;
	}
	
	public void setFkId(int id) {
		this.fk_id = id;
	}
	
	public void setId(String id) {
		if (id == null) {
			setId(0);
		} else {
		 setId(Integer.valueOf(id));
		}
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public int getCodImgPreview() {
		return this.cod_tag_img_preview;
	}
	
	public void setCodImgPreview(int id) {
		this.cod_tag_img_preview = id;
	}
}
