package br.com.schoolify.shoolify.services;

import br.com.schoolify.shoolify.dto.AtividadeDTO;
import br.com.schoolify.shoolify.model.Atividade;
import br.com.schoolify.shoolify.model.DiscProfTurma;
import br.com.schoolify.shoolify.repository.AtividadeRepository;
import br.com.schoolify.shoolify.repository.DiscProfTurmaRepository;
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
public class AtividadeService {

    @Autowired
    private AtividadeRepository repository;

    @Autowired
    private DiscProfTurmaRepository DiscProfTurmarepository;

    @Transactional(readOnly = true)
    public AtividadeDTO findById(Long id) {
        Atividade atividade = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new AtividadeDTO(atividade);
    }

    @Transactional(readOnly = true)
    public Page<AtividadeDTO> findAll(Pageable pageable) {
        Page<Atividade> result = repository.findAll(pageable);
        return result.map(x -> new AtividadeDTO(x));
    }

    @Transactional
    public AtividadeDTO insert(AtividadeDTO dto) {
        Atividade entity = new Atividade();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new AtividadeDTO(entity);
    }

    @Transactional
    public AtividadeDTO update(Long id, AtividadeDTO dto) {
        try {
            Atividade entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new AtividadeDTO(entity);
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

    private void copyDtoToEntity(AtividadeDTO dto, Atividade entity) {
        entity.setTitulo(dto.getTitulo());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataEntrega(dto.getDataEntrega());
        entity.setDescricao(dto.getDescricao());
        DiscProfTurma discProfTurma = DiscProfTurmarepository.findById(dto.getDiscprofturma_id())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        entity.setDiscProfTurma(discProfTurma);
    }
}
