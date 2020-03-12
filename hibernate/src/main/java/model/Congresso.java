package model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "congresso")
public class Congresso {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "idcongresso")
	private int id;
	
	@Column(name = "nome")
	private String nome;
	
	public Congresso(String nome) {
		this.nome = nome;
	}
	
	@OneToMany(mappedBy="congresso", targetEntity=Participante.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Participante> participante;
	
	public Congresso() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Participante> getParticipante() {
		return participante;
	}

	public void setParticipante(Set<Participante> participante) {
		this.participante = participante;
	}
	
}
