package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.model.TurmaPai;
import br.com.schoolify.shoolify.repository.TurmaPaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turmasPais")
public class TurmaPaiController {

    @Autowired
    private TurmaPaiRepository turmaPaiRepository;

    // GET - Listar todas as relações de TurmaPai
    @GetMapping
    public List<TurmaPai> listarTurmasPais() {
        return turmaPaiRepository.findAll();
    }

    // GET - Buscar relação TurmaPai por ID
    @GetMapping("/{id}")
    public ResponseEntity<TurmaPai> buscarTurmaPaiPorId(@PathVariable Integer id) {
        Optional<TurmaPai> turmaPai = turmaPaiRepository.findById(id);
        return turmaPai.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Criar uma nova relação TurmaPai
    @PostMapping
    public TurmaPai criarTurmaPai(@RequestBody TurmaPai turmaPai) {
        return turmaPaiRepository.save(turmaPai);
    }

    // PUT - Atualizar uma relação TurmaPai existente
    @PutMapping("/{id}")
    public ResponseEntity<TurmaPai> atualizarTurmaPai(@PathVariable Integer id, @RequestBody TurmaPai turmaPaiAtualizada) {
        Optional<TurmaPai> turmaPaiOptional = turmaPaiRepository.findById(id);
        if (turmaPaiOptional.isPresent()) {
            TurmaPai turmaPai = turmaPaiOptional.get();
            turmaPai.setTurmas(turmaPaiAtualizada.getTurmas());
            turmaPai.setUsuarios(turmaPaiAtualizada.getUsuarios());
            TurmaPai turmaPaiSalvo = turmaPaiRepository.save(turmaPai);
            return ResponseEntity.ok(turmaPaiSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Excluir uma relação TurmaPai
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTurmaPai(@PathVariable Integer id) {
        if (turmaPaiRepository.existsById(id)) {
            turmaPaiRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
