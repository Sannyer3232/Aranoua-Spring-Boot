package edu.aranoua.aplicacao_spring01.dto;

import edu.aranoua.aplicacao_spring01.model.Pais;

public class PaisOutputDTO {
    private Long id;
    private String nome;
    private String sigla;

    public PaisOutputDTO() {

    }

    public PaisOutputDTO(Pais pais) {
        this.id = pais.getId();
        this.sigla = pais.getSigla();
        this.nome = pais.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
