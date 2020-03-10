package classe;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import classe.Nota;

@Entity
@Table( name = "ALUNO" )
public class Aluno {
	@Id
	@Column(name = "ALU_MATRICULA")
	private String matricula;
	
	@Column(name = "ALU_NOME")
	private String nome;
			
	@OneToMany(mappedBy="aluno", targetEntity=Nota.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Nota> nota;
	
	public Aluno(String matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
	}

	public Aluno() {
		
	}

	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Set<Nota> getNota() {
		return nota;
	}


	public void setNota(Set<Nota> nota) {
		this.nota = nota;
	}	
	
	
}
