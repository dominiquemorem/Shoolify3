package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.dto.ComentarioDTO;
import br.com.schoolify.shoolify.dto.SugestaoLivroDTO;
import br.com.schoolify.shoolify.model.SugestaoLivro;
import br.com.schoolify.shoolify.repository.SugestaoLivroRepository;
import br.com.schoolify.shoolify.services.ComentarioService;
import br.com.schoolify.shoolify.services.SugestaoLivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sugestoesLivros")
public class SugestaoLivroController {

    @Autowired
    private SugestaoLivroService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SugestaoLivroDTO> findById(@PathVariable Long id) {
        SugestaoLivroDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<SugestaoLivroDTO>> findAll(Pageable pageable) {
        Page<SugestaoLivroDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<SugestaoLivroDTO> insert(@Valid @RequestBody SugestaoLivroDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SugestaoLivroDTO> update(@PathVariable Long id, @Valid @RequestBody SugestaoLivroDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}