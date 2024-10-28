package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.model.Comentario;
import br.com.schoolify.shoolify.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    // GET - Listar todos os comentários
    @GetMapping
    public List<Comentario> listarComentarios() {
        return comentarioRepository.findAll();
    }

    // GET - Buscar comentário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> buscarComentarioPorId(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);
        return comentario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Criar um novo comentário
    @PostMapping
    public Comentario criarComentario(@RequestBody Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // PUT - Atualizar um comentário existente
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> atualizarComentario(@PathVariable Long id, @RequestBody Comentario comentarioAtualizado) {
        Optional<Comentario> comentarioOptional = comentarioRepository.findById(id);
        if (comentarioOptional.isPresent()) {
            Comentario comentario = comentarioOptional.get();
            comentario.setConteudo(comentarioAtualizado.getConteudo());
            comentario.setDataHora(comentarioAtualizado.getDataHora());
            comentario.setAtividade(comentarioAtualizado.getAtividade());
            Comentario comentarioSalvo = comentarioRepository.save(comentario);
            return ResponseEntity.ok(comentarioSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Excluir um comentário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable Long id) {
        if (comentarioRepository.existsById(id)) {
            comentarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}