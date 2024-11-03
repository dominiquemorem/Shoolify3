package br.com.schoolify.shoolify.services;

import br.com.schoolify.shoolify.dto.TurmaDTO;
import br.com.schoolify.shoolify.model.Turma;
import br.com.schoolify.shoolify.repository.TurmaRepository;
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
public class TurmaService {

    @Autowired
    private TurmaRepository repository;

    @Transactional(readOnly = true)
    public TurmaDTO findById(Long id) {
        Turma turma = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new TurmaDTO(turma);
    }

    @Transactional(readOnly = true)
    public Page<TurmaDTO> findAll(Pageable pageable) {
        Page<Turma> result = repository.findAll(pageable);
        return result.map(x -> new TurmaDTO(x));
    }

    @Transactional
    public TurmaDTO insert(TurmaDTO dto) {
        Turma entity = new Turma();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new TurmaDTO(entity);
    }

    @Transactional
    public TurmaDTO update(Long id, TurmaDTO dto) {
        try {
            Turma entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new TurmaDTO(entity);
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

    private void copyDtoToEntity(TurmaDTO dto, Turma entity) {
        entity.setDescricao(dto.getDescricao());
    }
}
