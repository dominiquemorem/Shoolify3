package br.com.schoolify.shoolify.services;

import br.com.schoolify.shoolify.dto.UsuarioDTO;
import br.com.schoolify.shoolify.model.Usuario;
import br.com.schoolify.shoolify.repository.UsuarioRepository;
import br.com.schoolify.shoolify.services.exceptions.DatabaseException;
import br.com.schoolify.shoolify.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Usuario Usuario = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new UsuarioDTO(Usuario);
    }

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> result = repository.findAll(pageable);
        return result.map(x -> new UsuarioDTO(x));
    }

    @Transactional
    public UsuarioDTO insert(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new UsuarioDTO(entity);
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        try {
            Usuario entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new UsuarioDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setSenha(dto.getSenha());
    }
}
