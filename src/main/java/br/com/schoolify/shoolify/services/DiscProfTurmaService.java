package br.com.schoolify.shoolify.services;

import br.com.schoolify.shoolify.dto.DiscProfTurmaDTO;
import br.com.schoolify.shoolify.model.DiscProfTurma;
import br.com.schoolify.shoolify.model.Usuario;
import br.com.schoolify.shoolify.repository.DiscProfTurmaRepository;
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
public class DiscProfTurmaService {

    @Autowired
    private DiscProfTurmaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public DiscProfTurmaDTO findById(Long id) {
        DiscProfTurma DiscProfTurma = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new DiscProfTurmaDTO(DiscProfTurma);
    }

    @Transactional(readOnly = true)
    public Page<DiscProfTurmaDTO> findAll(Pageable pageable) {
        Page<DiscProfTurma> result = repository.findAll(pageable);
        return result.map(x -> new DiscProfTurmaDTO(x));
    }

    @Transactional
    public DiscProfTurmaDTO insert(DiscProfTurmaDTO dto) {
        DiscProfTurma entity = new DiscProfTurma();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new DiscProfTurmaDTO(entity);
    }

    @Transactional
    public DiscProfTurmaDTO update(Long id, DiscProfTurmaDTO dto) {
        try {
            DiscProfTurma entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new DiscProfTurmaDTO(entity);
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

    private void copyDtoToEntity(DiscProfTurmaDTO dto, DiscProfTurma entity) {
        if (dto.getUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
            entity.setUsuario(usuario);
        }
    }
}
