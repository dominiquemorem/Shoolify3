package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.model.DiscProfTurma;
import br.com.schoolify.shoolify.repository.DiscProfTurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/discProfTurma")
public class DiscProfTurmaController {

    @Autowired
    private DiscProfTurmaRepository discProfTurmaRepository;

    // GET - Listar todas as relações DiscProfTurma
    @GetMapping
    public List<DiscProfTurma> listarDiscProfTurma() {
        return discProfTurmaRepository.findAll();
    }

    // GET - Buscar relação DiscProfTurma por ID
    @GetMapping("/{id}")
    public ResponseEntity<DiscProfTurma> buscarDiscProfTurmaPorId(@PathVariable Integer id) {
        Optional<DiscProfTurma> discProfTurma = discProfTurmaRepository.findById(id);
        return discProfTurma.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Criar uma nova relação DiscProfTurma
    @PostMapping
    public DiscProfTurma criarDiscProfTurma(@RequestBody DiscProfTurma discProfTurma) {
        return discProfTurmaRepository.save(discProfTurma);
    }

    // PUT - Atualizar uma relação DiscProfTurma existente
    @PutMapping("/{id}")
    public ResponseEntity<DiscProfTurma> atualizarDiscProfTurma(@PathVariable Integer id, @RequestBody DiscProfTurma discProfTurmaAtualizada) {
        Optional<DiscProfTurma> discProfTurmaOptional = discProfTurmaRepository.findById(id);
        if (discProfTurmaOptional.isPresent()) {
            DiscProfTurma discProfTurma = discProfTurmaOptional.get();
            discProfTurma.setDisciplinas(discProfTurmaAtualizada.getDisciplinas());
            discProfTurma.setUsuario(discProfTurmaAtualizada.getUsuario());
            discProfTurma.setTurmas(discProfTurmaAtualizada.getTurmas());
            DiscProfTurma discProfTurmaSalvo = discProfTurmaRepository.save(discProfTurma);
            return ResponseEntity.ok(discProfTurmaSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Excluir uma relação DiscProfTurma
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiscProfTurma(@PathVariable Integer id) {
        if (discProfTurmaRepository.existsById(id)) {
            discProfTurmaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}