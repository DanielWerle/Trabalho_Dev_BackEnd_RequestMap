package org.example.trabalhodevback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "jogo")
@Getter
@Setter
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Integer id;

    public String nome;
    public double preco;
    public String genero;

    public Jogo(Integer id, String nome, double preco, String genero) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.genero = genero;
    }

    public Jogo(){

    }
}