package classe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	@OneToOne
	@JoinColumn(name = "fk_participante", nullable = false)
	private Participante participante;
	
	@OneToOne
	@JoinColumn(name = "fk_artigo", nullable = false)
	private Artigo artigo;
	
	public Autor(Participante participante, Artigo artigo) {
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

	public Artigo getArtigo() {
		return artigo;
	}

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}
	
}
