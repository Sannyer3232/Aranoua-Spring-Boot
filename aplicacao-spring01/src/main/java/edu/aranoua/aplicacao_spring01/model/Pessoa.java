package edu.aranoua.aplicacao_spring01.model;

import jakarta.persistence.*;

@Entity(name = "pessoa")
public class Pessoa {

        @Id()
        @Column(name="pesid")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name="pescpf", nullable=false, unique=true, length=11)
        private String cpf;

        @Column(name="pesnome", nullable = false)
        private String nome;

        @Column(name ="pesidade", nullable = true)
        private int idade;

        @ManyToOne
        private Cidade cidade;

        public Pessoa() {
        }

        public Cidade getCidade() {
            return cidade;
        }

        public void setCidade(Cidade cidade) {
            this.cidade = cidade;
            cidade.addPessoa(this);
        }

        public Pessoa(String cpf, String nome, int idade) {
            this.cpf = cpf;
            this.nome = nome;
            this.idade = idade;
        }

        public Pessoa(long id, String cpf, int idade, String nome) {
            this.id = id;
            this.cpf = cpf;
            this.idade = idade;
            this.nome = nome;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
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

        @Override
        public String toString() {
            return "Pessoa{" +
                    "id=" + id +
                    ", cpf='" + cpf + '\'' +
                    ", nome='" + nome + '\'' +
                    ", idade=" + idade +
                    ", cidade=" + cidade +
                    '}';
        }
}
