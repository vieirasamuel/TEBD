package classe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "autor")
public class Autor {

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "idautor")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "fk_participante", nullable = false)
	private Participante participante;
	
	@ManyToOne
	@Column(name = "fk_artigo")
	private int artigo;
	
	public Autor(Participante participante, int artigo) {
		this.participante = participante;
		this.artigo = artigo;
	}
	
	public Autor() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public int getArtigo() {
		return artigo;
	}

	public void setArtigo(int artigo) {
		this.artigo = artigo;
	}
	
}
