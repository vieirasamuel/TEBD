package classe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "artigo")
public class Artigo {

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "idartigo")
	private int id;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "resumo")
	private String resumo;
	
	public Artigo(String titulo, String resumo) {
		this.titulo = titulo;
		this.resumo = resumo;
	}
	
	public Artigo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

//	public Autor getAutor() {
//		return autor;
//	}
//
//	public void setAutor(Autor autor) {
//		this.autor = autor;
//	}
	
}
