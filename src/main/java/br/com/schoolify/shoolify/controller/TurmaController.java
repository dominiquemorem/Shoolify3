package br.com.schoolify.shoolify.controller;


import br.com.schoolify.shoolify.model.Turma;
import br.com.schoolify.shoolify.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    // GET - Listar todas as turmas
    @GetMapping
    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }

    // GET - Buscar turma por ID
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarTurmaPorId(@PathVariable Long id) {
        Optional<Turma> turma = turmaRepository.findById(id);
        return turma.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Criar uma nova turma
    @PostMapping
    public Turma criarTurma(@RequestBody Turma turma) {
        return turmaRepository.save(turma);
    }

    // PUT - Atualizar uma turma existente
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable Long id, @RequestBody Turma turmaAtualizada) {
        Optional<Turma> turmaOptional = turmaRepository.findById(id);
        if (turmaOptional.isPresent()) {
            Turma turma = turmaOptional.get();
            turma.setDescricao(turmaAtualizada.getDescricao());
            Turma turmaSalva = turmaRepository.save(turma);
            return ResponseEntity.ok(turmaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Excluir uma turma
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Long id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
