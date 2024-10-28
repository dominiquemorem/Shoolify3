package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.model.Evento;
import br.com.schoolify.shoolify.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    // GET - Listar todos os eventos
    @GetMapping
    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    // GET - Buscar evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventoPorId(@PathVariable Long id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Criar um novo evento
    @PostMapping
    public Evento criarEvento(@RequestBody Evento evento) {
        return eventoRepository.save(evento);
    }

    // PUT - Atualizar um evento existente
    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @RequestBody Evento eventoAtualizado) {
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            evento.setNome(eventoAtualizado.getNome());
            evento.setDataInicio(eventoAtualizado.getDataInicio());
            evento.setHoraInicio(eventoAtualizado.getHoraInicio());
            evento.setDescricao(eventoAtualizado.getDescricao());
            evento.setUsuario(eventoAtualizado.getUsuario());
            Evento eventoSalvo = eventoRepository.save(evento);
            return ResponseEntity.ok(eventoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Excluir um evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}