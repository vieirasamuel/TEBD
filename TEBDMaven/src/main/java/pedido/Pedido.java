package pedido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;






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
@Table( name = "PEDIDO" )
public class Pedido {
    
    private int id;
    
    private String descricao;

    public Pedido() {
        
        
    }

    public Pedido(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

        @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

        @Column(name = "DESCRICAO")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
    
}
