package com.example.tebdandroid;

import java.io.Serializable;

public class ArtigoValue implements Serializable {

    private Long id;
    private String artigo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtigo() {
        return artigo;
    }

    public void setArtigo(String artigo) {
        this.artigo = artigo;
    }

    public String toString(){
        return this.getArtigo();
    };
}
