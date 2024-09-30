package edu.aranoua.aplicacao_spring01.dto;

import edu.aranoua.aplicacao_spring01.model.Pessoa;

public class PessoaOutputDTO {

    private Long id;
    private String cpf;
    private String nome;
    private int idade;
    private String cidade;

    public PessoaOutputDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.cpf = pessoa.getCpf();
        this.nome = pessoa.getNome();
        this.idade = pessoa.getIdade();
        this.cidade = pessoa.getCidade().getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

}
