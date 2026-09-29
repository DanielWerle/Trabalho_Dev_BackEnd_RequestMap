package org.example.trabalhodevback.controller;

import org.example.trabalhodevback.model.Jogo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/jogo")
public class JogoController {
    List<Jogo> jogos = new ArrayList<>();

    @GetMapping("/listar-jogos")
    public ResponseEntity<List<Jogo>> listarJogo(){
        return ResponseEntity.ok(jogos);
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<Jogo> buscarPorIdJogo(@PathVariable int id) {
        for (Jogo jogo : jogos) {
            if (id == jogo.id) {
                return ResponseEntity.ok(jogo);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Jogo>> buscarJogo(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Double preco,
            @RequestParam(required = false) String genero) {

        List<Jogo> resultado = new ArrayList<>();

        for (Jogo jogo : jogos) {
            if (nome != null && !jogo.nome.toLowerCase().startsWith(nome.toLowerCase())) {
                continue;
            }

            if (genero != null && !jogo.genero.equalsIgnoreCase(genero)) {
                continue;
            }

            if (preco != null && jogo.preco > preco) {
                continue;
            }

            resultado.add(jogo);
        }

        if (!resultado.isEmpty()) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Jogo> cadastrarJogo(@RequestBody (required = false) Jogo jogo){
        if(jogo == null){
            return ResponseEntity.badRequest().build();
        } else {
            jogos.add(jogo);
            return ResponseEntity.ok(jogo);
        }
    }

    //Pesquisa externa para adicionar mais dados para a pesquisa com filtros;
    @PostMapping("/cadastrar-jogos")
    public ResponseEntity<List<Jogo>> criarJogos(@RequestBody List<Jogo> novosJogos) {
        jogos.addAll(novosJogos);
        return ResponseEntity.ok(novosJogos);
    }
    //

    @PutMapping("/editar/{id}")
    public ResponseEntity<Jogo> editarJogo(@PathVariable int id, @RequestBody Jogo jogo){
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).id == id) {
                Jogo j = jogos.get(i);
                j.setNome(jogo.getNome());
                j.setPreco(jogo.getPreco());
                j.setGenero(jogo.getGenero());
                jogos.set(id, j);

                return ResponseEntity.ok(j);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarJogo(@PathVariable int id) {
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).id == id) {
                String nome = jogos.get(i).getNome();
                jogos.remove(i);

                return ResponseEntity.ok("Jogo " + id + " (" + nome + ") " + " deletado com sucesso");
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
