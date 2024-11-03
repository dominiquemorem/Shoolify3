package br.com.schoolify.shoolify.services;

import br.com.schoolify.shoolify.dto.DisciplinaDTO;
import br.com.schoolify.shoolify.model.Disciplina;
import br.com.schoolify.shoolify.repository.DisciplinaRepository;
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
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository repository;

    @Transactional(readOnly = true)
    public DisciplinaDTO findById(Long id) {
        Disciplina disciplina = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new DisciplinaDTO(disciplina);
    }

    @Transactional(readOnly = true)
    public Page<DisciplinaDTO> findAll(Pageable pageable) {
        Page<Disciplina> result = repository.findAll(pageable);
        return result.map(x -> new DisciplinaDTO(x));
    }

    @Transactional
    public DisciplinaDTO insert(DisciplinaDTO dto) {
        Disciplina entity = new Disciplina();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new DisciplinaDTO(entity);
    }

    @Transactional
    public DisciplinaDTO update(Long id, DisciplinaDTO dto) {
        try {
            Disciplina entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new DisciplinaDTO(entity);
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

    private void copyDtoToEntity(DisciplinaDTO dto, Disciplina entity) {
        entity.setNome(dto.getNome());
        entity.setImgUrl(dto.getImgUrl());

    }
}
