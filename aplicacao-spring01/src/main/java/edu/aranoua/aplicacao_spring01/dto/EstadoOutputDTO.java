package edu.aranoua.aplicacao_spring01.dto;

import edu.aranoua.aplicacao_spring01.model.Estado;
import edu.aranoua.aplicacao_spring01.repository.EstadoRepository;

public class EstadoOutputDTO {
    private Long id;
    private String nome;
    private String sigla;

    public EstadoOutputDTO(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.sigla = estado.getSigla();
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
