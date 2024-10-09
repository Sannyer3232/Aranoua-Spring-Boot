package edu.aranoua.aplicacao_spring01.dto;

import edu.aranoua.aplicacao_spring01.model.Estado;
import edu.aranoua.aplicacao_spring01.repository.EstadoRepository;
import edu.aranoua.aplicacao_spring01.repository.PaisRepository;

public class EstadoInputDTO {

    private String nome;
    private String sigla;
    private String pais;

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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Estado build(PaisRepository  paisRepository){
        Estado estado = new Estado();
        estado.setNome(this.nome);
        estado.setSigla(this.sigla);
        estado.setPais(paisRepository.findByNome(this.pais));
        return estado;
    }

}
