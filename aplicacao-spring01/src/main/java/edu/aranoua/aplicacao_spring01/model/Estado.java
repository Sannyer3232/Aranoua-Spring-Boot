package edu.aranoua.aplicacao_spring01.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity(name = "estado")
public class Estado {

        @Id
        @Column(name = "estadocodigo")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @Column(name = "estadonome", nullable = false, length = 50)
        private String nome;
        @Column(name = "estadosigla", nullable = false)
        private String sigla;

        public Estado() {}

        public Estado(int id, String nome, String sigla) {
            this.id = id;
            this.nome = nome;
            this.sigla = sigla;
        }

        public Estado(String sigla, String nome) {
            this.sigla = sigla;
            this.nome = nome;
        }

        public long getId() {
            return id;
        }

        public void setId(int id) {
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
