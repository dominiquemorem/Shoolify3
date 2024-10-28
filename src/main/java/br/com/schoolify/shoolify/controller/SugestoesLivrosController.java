package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.model.SugestaoLivro;
import br.com.schoolify.shoolify.repository.SugestoesLivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sugestoesLivros")
public class SugestoesLivrosController {

    @Autowired
    private SugestoesLivrosRepository sugestoesLivrosRepository;

    // GET - Listar todas as sugestões de livros
    @GetMapping
    public List<SugestaoLivro> listarSugestoesLivros() {
        return sugestoesLivrosRepository.findAll();
    }

    // GET - Buscar sugestão de livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<SugestaoLivro> buscarSugestaoLivrosPorId(@PathVariable Long id) {
        Optional<SugestaoLivro> sugestaoLivros = sugestoesLivrosRepository.findById(id);
        return sugestaoLivros.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Criar uma nova sugestão de livro
    @PostMapping
    public SugestaoLivro criarSugestaoLivros(@RequestBody SugestaoLivro sugestaoLivro) {
        return sugestoesLivrosRepository.save(sugestaoLivro);
    }

    // PUT - Atualizar uma sugestão de livro existente
    @PutMapping("/{id}")
    public ResponseEntity<SugestaoLivro> atualizarSugestaoLivros(@PathVariable Long id, @RequestBody SugestaoLivro sugestaoLivroAtualizada) {
        Optional<SugestaoLivro> sugestaoLivrosOptional = sugestoesLivrosRepository.findById(id);
        if (sugestaoLivrosOptional.isPresent()) {
            SugestaoLivro sugestaoLivro = sugestaoLivrosOptional.get();
            sugestaoLivro.setNome(sugestaoLivroAtualizada.getNome());
            sugestaoLivro.setCapa(sugestaoLivroAtualizada.getCapa());
            sugestaoLivro.setLinkLivros(sugestaoLivroAtualizada.getLinkLivros());
            SugestaoLivro sugestaoLivroSalva = sugestoesLivrosRepository.save(sugestaoLivro);
            return ResponseEntity.ok(sugestaoLivroSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Excluir uma sugestão de livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSugestaoLivros(@PathVariable Long id) {
        if (sugestoesLivrosRepository.existsById(id)) {
            sugestoesLivrosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}