/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author aluno
 */

@Entity
@Table( name = "Nota" )
public class Nota {
    private int codigo;
    private Aluno aluno;
    private float nota;

    public Nota(Aluno aluno, float nota) {
        this.codigo = codigo;
        this.aluno = aluno;
        this.nota = nota;
    }
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        @Column(name = "not_codigo")
    public int getCodigo() {
        return codigo;
    }
        
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
        @ManyToOne
        @JoinColumn(name="alu_matricula", nullable=false)
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
        @Column(name = "not_nota")
    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
    
    
    
    
    
}
