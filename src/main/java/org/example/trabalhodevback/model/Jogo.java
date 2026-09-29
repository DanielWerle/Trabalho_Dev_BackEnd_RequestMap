package org.example.trabalhodevback.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Jogo {
    public int id;
    public String nome;
    public double preco;
    public String genero;

    public Jogo(int id, String nome, double preco, String genero) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.genero = genero;
    }

    public Jogo(){

    }
}