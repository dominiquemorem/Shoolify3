package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.dto.AtividadeDTO;
import br.com.schoolify.shoolify.dto.TipoUsuarioDTO;
import br.com.schoolify.shoolify.model.TipoUsuario;
import br.com.schoolify.shoolify.repository.TipoUsuarioRepository;
import br.com.schoolify.shoolify.services.AtividadeService;
import br.com.schoolify.shoolify.services.TipoUsuarioService;
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
@RequestMapping("/tipoUsuarios")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TipoUsuarioDTO> findById(@PathVariable Long id) {
        TipoUsuarioDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<TipoUsuarioDTO>> findAll(Pageable pageable) {
        Page<TipoUsuarioDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioDTO> insert(@Valid @RequestBody TipoUsuarioDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TipoUsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody TipoUsuarioDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
