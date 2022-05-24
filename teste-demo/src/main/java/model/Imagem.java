package model;

import java.sql.Date;

public class Imagem {
	private int id;
	private int autor_id;
	private String img_path;
	private String img_descricao;
	private Date data_envio;
	
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setId(String id) {
		if (id == null) {
			setId(0);
		} else {
		 setId(Integer.valueOf(id));
		}
	}

	public int getAutorId() {
		return this.autor_id;
	}
	
	public void setAutorId(int id) {
		this.autor_id = id;
	}
	
	public String getImgPath() {
		return this.img_path;
	}
	public void setImgPath(String path) {
		this.img_path = path;
	}
	
	public String getDescricao() {
		return this.img_descricao;
	}
	public void setDescricao(String descricao) {
		this.img_descricao = descricao;
	}
	
	public Date getDataEnvio() {
		return this.data_envio;
	}
	
	public void setDataEnvio(Date data) {
		this.data_envio = data;
	}
}
