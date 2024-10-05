package edu.aranoua.aplicacao_spring01.dto;

import edu.aranoua.aplicacao_spring01.model.Pais;

public class PaisInputDTO {
    private String nome;
    private String sigla;

    public PaisInputDTO() {
    }

    public PaisInputDTO(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Pais build() {
        Pais pais = new Pais();
        pais.setNome(this.nome);
        pais.setSigla(this.sigla);
        return pais;
    }
}
