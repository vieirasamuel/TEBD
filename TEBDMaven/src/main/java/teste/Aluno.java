package teste;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aluno
 */

@Entity
@Table( name = "Aluno" )
public class Aluno {
    
    private String matricula;
    private String nome;
    private List<Nota> nota;

    public Aluno(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }
        @Id
        @Column(name = "matricula")
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
        @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
        @OneToMany(cascade = {CascadeType.ALL})
    public List<Nota> getNota() {
        return nota;
    }

    public void setNota(List<Nota> nota) {
        this.nota = nota;
    }
    
                
    
    
    
}
