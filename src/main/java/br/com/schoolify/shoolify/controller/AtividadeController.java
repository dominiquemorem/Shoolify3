package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.model.Atividade;
import br.com.schoolify.shoolify.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeRepository atividadeRepository;

    // GET - Listar todas as atividades
    @GetMapping
    public List<Atividade> listarAtividades() {
        return atividadeRepository.findAll();
    }

    // GET - Buscar atividade por ID
    @GetMapping("/{id}")
    public ResponseEntity<Atividade> buscarAtividadePorId(@PathVariable Long id) {
        Optional<Atividade> atividade = atividadeRepository.findById(id);
        return atividade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Criar uma nova atividade
    @PostMapping
    public Atividade criarAtividade(@RequestBody Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    // PUT - Atualizar uma atividade existente
    @PutMapping("/{id}")
    public ResponseEntity<Atividade> atualizarAtividade(@PathVariable Long id, @RequestBody Atividade atividadeAtualizada) {
        Optional<Atividade> atividadeOptional = atividadeRepository.findById(id);
        if (atividadeOptional.isPresent()) {
            Atividade atividade = atividadeOptional.get();
            atividade.setTitulo(atividadeAtualizada.getTitulo());
            atividade.setDataInicio(atividadeAtualizada.getDataInicio());
            atividade.setDataEntrega(atividadeAtualizada.getDataEntrega());
            atividade.setDescricao(atividadeAtualizada.getDescricao());
            atividade.setDiscProfTurma(atividadeAtualizada.getDiscProfTurma());
            Atividade atividadeSalva = atividadeRepository.save(atividade);
            return ResponseEntity.ok(atividadeSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Excluir uma atividade
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtividade(@PathVariable Long id) {
        if (atividadeRepository.existsById(id)) {
            atividadeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}