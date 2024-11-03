package br.com.schoolify.shoolify.services;

import br.com.schoolify.shoolify.dto.TipoUsuarioDTO;
import br.com.schoolify.shoolify.model.TipoUsuario;
import br.com.schoolify.shoolify.repository.TipoUsuarioRepository;
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
public class TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository repository;


    @Transactional(readOnly = true)
    public TipoUsuarioDTO findById(Long id) {
        TipoUsuario tipoUsuario = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new TipoUsuarioDTO(tipoUsuario);
    }

    @Transactional(readOnly = true)
    public Page<TipoUsuarioDTO> findAll(Pageable pageable) {
        Page<TipoUsuario> result = repository.findAll(pageable);
        return result.map(x -> new TipoUsuarioDTO(x));
    }

    @Transactional
    public TipoUsuarioDTO insert(TipoUsuarioDTO dto) {
        TipoUsuario entity = new TipoUsuario();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new TipoUsuarioDTO(entity);
    }

    @Transactional
    public TipoUsuarioDTO update(Long id, TipoUsuarioDTO dto) {
        try {
            TipoUsuario entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new TipoUsuarioDTO(entity);
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

    private void copyDtoToEntity(TipoUsuarioDTO dto, TipoUsuario entity) {
        entity.setTipoUsuario(dto.getTipoUsuario());
    }
}
