package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import model.Aluno;

import javax.persistence.Column;

@Entity
@Table( name = "NOTA" )
public class Nota {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "NOT_CODIGO")
	private int codigo;
	
	@ManyToOne
	@JoinColumn(name="ALU_MATRICULA", nullable=false)
	private Aluno aluno;
	
	@Column(name = "NOT_NOTA")
	private double nota;
	
	public Nota(Aluno aluno, double nota) {
		this.aluno = aluno;
		this.nota = nota;
	}
	
	

	public Nota() {
	}



	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}
	
	
	
	
}
