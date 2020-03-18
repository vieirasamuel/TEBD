package model;

public class Artigo {
	private String titulo;
	private Double nota;
	
	
	public Artigo(String titulo, Double nota) {
		this.titulo = titulo;
		this.nota = nota;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	
}
