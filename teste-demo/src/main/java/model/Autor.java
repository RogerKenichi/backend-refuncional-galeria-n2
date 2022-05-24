package model;


public class Autor {
	private int id;
	private String nome;
	private String descricao;
	private int cod_autor_img_preview;
	
	
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
	
	public String getName() {
		return this.nome;
	}
	public void setName(String name) {
		this.nome = name;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCodImgPreview() {
		return this.cod_autor_img_preview;
	}
	
	public void setCodImgPreview(int id) {
		this.cod_autor_img_preview = id;
	}
}
