package edu.aranoua.aplicacao_spring01.model;

import jakarta.persistence.*;

@Entity(name = "pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paiscodigo")
    private Long id;
    @Column(name = "paisnome", nullable = false, length = 50)
    private String nome;
    @Column(name = "paissigla", nullable = false)
    private String sigla;

    public Pais() {
    }

    public Pais(Long id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
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
