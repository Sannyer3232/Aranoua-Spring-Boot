package edu.aranoua.aplicacao_spring01.dto;

import edu.aranoua.aplicacao_spring01.model.Estado;
import edu.aranoua.aplicacao_spring01.repository.EstadoRepository;

public class EstadoInputDTO {

    private String nome;
    private String sigla;

    public EstadoInputDTO() {}

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

    public Estado build(EstadoRepository estadoRepository){
        Estado estado = new Estado();
        estado.setNome(this.nome);
        estado.setSigla(this.sigla);
        return estado;
    }

}
