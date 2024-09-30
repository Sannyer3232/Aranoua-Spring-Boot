package edu.aranoua.aplicacao_spring01.dto;

import edu.aranoua.aplicacao_spring01.model.Pessoa;
import edu.aranoua.aplicacao_spring01.repository.CidadeRepository;

public class PessoaInputDTO {

    private String cpf;
    private String nome;
    private int idade;
    private String cidade;

    public PessoaInputDTO() {}

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

    public Pessoa build(CidadeRepository cidadeRepository) {

        Pessoa pessoa = new Pessoa();

        pessoa.setCpf(this.cpf);
        pessoa.setNome(this.nome);
        pessoa.setIdade(this.idade);
        pessoa.setCidade(cidadeRepository.findByNome(this.cidade));

        return pessoa;

    }
}
