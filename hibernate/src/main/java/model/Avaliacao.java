package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "idavaliacao")
	private int id;
	
	@Column(name = "nota")
	private String nota;
	
	@Column(name = "comentario")
	private String comentario;
	
	@OneToOne
	@JoinColumn(name = "fk_participante", nullable = false)
	private Participante participante;
	
	@OneToOne
	@JoinColumn(name = "fk_artigo", nullable = false)
	private Artigo artigo;
	
	public Avaliacao() {
		
	}
	
	public Avaliacao(String nota, String comentario, Artigo artigo, Participante participante) {
		this.nota = nota;
		this.comentario = comentario;
		this.artigo = artigo;
		this.participante = participante;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
}
