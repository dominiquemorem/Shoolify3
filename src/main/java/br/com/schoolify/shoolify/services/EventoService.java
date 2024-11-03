package br.com.schoolify.shoolify.services;

import br.com.schoolify.shoolify.dto.EventoDTO;
import br.com.schoolify.shoolify.model.Evento;
import br.com.schoolify.shoolify.repository.EventoRepository;
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
public class EventoService {

    @Autowired
    private EventoRepository repository;

    @Transactional(readOnly = true)
    public EventoDTO findById(Long id) {
        Evento evento = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new EventoDTO(evento);
    }

    @Transactional(readOnly = true)
    public Page<EventoDTO> findAll(Pageable pageable) {
        Page<Evento> result = repository.findAll(pageable);
        return result.map(x -> new EventoDTO(x));
    }

    @Transactional
    public EventoDTO insert(EventoDTO dto) {
        Evento entity = new Evento();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new EventoDTO(entity);
    }

    @Transactional
    public EventoDTO update(Long id, EventoDTO dto) {
        try {
            Evento entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new EventoDTO(entity);
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

    private void copyDtoToEntity(EventoDTO dto, Evento entity) {
        entity.setNome(dto.getNome());
        entity.setDataInicio(dto.getDataInicio());
        entity.setHoraInicio(dto.getHoraInicio());
        entity.setDescricao(dto.getDescricao());
    }
}
