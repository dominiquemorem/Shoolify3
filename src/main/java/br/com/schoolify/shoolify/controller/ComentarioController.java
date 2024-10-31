package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.dto.ComentarioDTO;
import br.com.schoolify.shoolify.repository.ComentarioRepository;
import br.com.schoolify.shoolify.services.AtividadeService;
import br.com.schoolify.shoolify.services.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ComentarioDTO> findById(@PathVariable Long id) {
        ComentarioDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ComentarioDTO>> findAll(Pageable pageable) {
        Page<ComentarioDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> insert(@Valid @RequestBody ComentarioDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ComentarioDTO> update(@PathVariable Long id, @Valid @RequestBody ComentarioDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}