package edu.aranoua.aplicacao_spring01.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Cidade {
    @Id
    @Column(name = "cidcodigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="cidnome", nullable=false, unique=true, length=100)
    private String nome;

    @ManyToOne
    private Estado estado;

    @OneToMany(mappedBy = "cidade")
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Cidade(String nome) {
        this.nome = nome;
    }

    public Cidade() {}

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void addPessoa(Pessoa pessoa){
        if(!this.pessoas.contains(pessoa)){
            this.pessoas.add(pessoa);
            pessoa.setCidade(this);
        }
    }


    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estado=" + estado +
                '}';
    }
}
