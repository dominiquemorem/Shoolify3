package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.model.Disciplina;
import br.com.schoolify.shoolify.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    // GET - Listar todas as disciplinas
    @GetMapping
    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    // GET - Buscar disciplina por ID
    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarDisciplinaPorId(@PathVariable Long id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        return disciplina.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Criar uma nova disciplina
    @PostMapping
    public Disciplina criarDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    // PUT - Atualizar uma disciplina existente
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizarDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplinaAtualizada) {
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(id);
        if (disciplinaOptional.isPresent()) {
            Disciplina disciplina = disciplinaOptional.get();
            disciplina.setNome(disciplinaAtualizada.getNome());
            disciplina.setImgUrl(disciplinaAtualizada.getImgUrl());
            Disciplina disciplinaSalva = disciplinaRepository.save(disciplina);
            return ResponseEntity.ok(disciplinaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Excluir uma disciplina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable Long id) {
        if (disciplinaRepository.existsById(id)) {
            disciplinaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}