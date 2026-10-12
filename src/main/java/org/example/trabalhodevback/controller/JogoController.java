package org.example.trabalhodevback.controller;

import org.example.trabalhodevback.model.Jogo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.example.trabalhodevback.repository.JogoRepository;

@RestController
@RequestMapping("/jogo")
public class JogoController {
    private final JogoRepository repository;

    public JogoController(JogoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/listar-jogos")
    public ResponseEntity<List<Jogo>> listarJogo() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<Jogo> buscarPorIdJogo(@PathVariable int id) {
        Optional<Jogo> jogo = repository.findById(id);

        if (jogo.isPresent()) {
            return ResponseEntity.ok(jogo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Jogo>> buscarJogo(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Double preco,
            @RequestParam(required = false) String genero) {

        List<Jogo> resultado = repository.buscarComFiltro(nome, preco, genero);

        if (!resultado.isEmpty()) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Jogo> cadastrarJogo(@RequestBody(required = false) Jogo jogo) {
        if (jogo == null) {
            return ResponseEntity.badRequest().build();
        } else {
            Jogo jogoSalvo = repository.save(jogo);
            return ResponseEntity.ok(jogoSalvo);
        }
    }

    //Pesquisa externa para adicionar mais dados para a pesquisa com filtros;
    @PostMapping("/cadastrar-jogos")
    public ResponseEntity<List<Jogo>> criarJogos(@RequestBody List<Jogo> novosJogos) {
        List<Jogo> jogosSalvos = repository.saveAll(novosJogos);
        return ResponseEntity.ok(jogosSalvos);
    }
    //

    @PutMapping("/editar/{id}")
    public ResponseEntity<Jogo> editarJogo(@PathVariable Integer id, @RequestBody Jogo jogo) {
        Optional<Jogo> jogoEncontrado = repository.findById(id);

        if (jogoEncontrado.isPresent()) {
            Jogo j = jogoEncontrado.get();

            j.setNome(jogo.getNome());
            j.setPreco(jogo.getPreco());
            j.setGenero(jogo.getGenero());
            Jogo jogoSalvo = repository.save(j);

            return ResponseEntity.ok(jogoSalvo);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarJogo(@PathVariable int id) {
        Optional<Jogo> jogoEncontrado = repository.findById(id);

        if (jogoEncontrado.isPresent()) {
            String nome = jogoEncontrado.get().getNome();
            repository.deleteById(id);

            return ResponseEntity.ok("Jogo " + id + " (" + nome + ") " + " deletado com sucesso");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}