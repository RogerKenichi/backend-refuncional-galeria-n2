package model;


public class Imagemxtags {
	private int imagem_id;
	private int tag_id;
	
	
	public int getImagemId() {
		return this.imagem_id;
	}
	
	public void setImagemId(int id) {
		this.imagem_id = id;
	}
	
	public void setImagemId(String id) {
		if (id == null) {
			setImagemId(0);
		} else {
			setImagemId(Integer.valueOf(id));
		}
	}

	public int getTagId() {
		return this.tag_id;
	}
	
	public void setTagId(int id) {
		this.tag_id = id;
	}
	
	public void setTagId(String id) {
		if (id == null) {
			setTagId(0);
		} else {
			setTagId(Integer.valueOf(id));
		}
	}
}
