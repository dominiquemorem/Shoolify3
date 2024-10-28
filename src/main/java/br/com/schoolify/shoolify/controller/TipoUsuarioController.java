package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.model.TipoUsuario;
import br.com.schoolify.shoolify.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tiposUsuarios")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    // GET - Listar todos os tipos de usuários
    @GetMapping
    public List<TipoUsuario> listarTiposUsuarios() {
        return tipoUsuarioRepository.findAll();
    }

    // GET - Buscar tipo de usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> buscarTipoUsuarioPorId(@PathVariable Long id) {
        Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepository.findById(id);
        return tipoUsuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Criar um novo tipo de usuário
    @PostMapping
    public TipoUsuario criarTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    // PUT - Atualizar um tipo de usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> atualizarTipoUsuario(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuarioAtualizado) {
        Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(id);
        if (tipoUsuarioOptional.isPresent()) {
            TipoUsuario tipoUsuario = tipoUsuarioOptional.get();
            //tipoUsuario.setDescricao(tipoUsuarioAtualizado.getDescricao());
            TipoUsuario tipoUsuarioSalvo = tipoUsuarioRepository.save(tipoUsuario);
            return ResponseEntity.ok(tipoUsuarioSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Excluir um tipo de usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoUsuario(@PathVariable Long id) {
        if (tipoUsuarioRepository.existsById(id)) {
            tipoUsuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
