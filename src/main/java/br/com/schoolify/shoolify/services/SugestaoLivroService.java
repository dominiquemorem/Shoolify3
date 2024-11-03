package br.com.schoolify.shoolify.services;

import br.com.schoolify.shoolify.dto.SugestaoLivroDTO;
import br.com.schoolify.shoolify.model.DiscProfTurma;
import br.com.schoolify.shoolify.model.SugestaoLivro;
import br.com.schoolify.shoolify.repository.DiscProfTurmaRepository;
import br.com.schoolify.shoolify.repository.SugestaoLivroRepository;
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
public class SugestaoLivroService {

    @Autowired
    private SugestaoLivroRepository repository;

    @Autowired
    private DiscProfTurmaRepository discProfTurmaRepository;

    @Transactional(readOnly = true)
    public SugestaoLivroDTO findById(Long id) {
        SugestaoLivro sugestaoLivro = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new SugestaoLivroDTO(sugestaoLivro);
    }

    @Transactional(readOnly = true)
    public Page<SugestaoLivroDTO> findAll(Pageable pageable) {
        Page<SugestaoLivro> result = repository.findAll(pageable);
        return result.map(x -> new SugestaoLivroDTO(x));
    }

    @Transactional
    public SugestaoLivroDTO insert(SugestaoLivroDTO dto) {
        SugestaoLivro entity = new SugestaoLivro();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new SugestaoLivroDTO(entity);
    }

    @Transactional
    public SugestaoLivroDTO update(Long id, SugestaoLivroDTO dto) {
        try {
            SugestaoLivro entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new SugestaoLivroDTO(entity);
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

    private void copyDtoToEntity(SugestaoLivroDTO dto, SugestaoLivro entity) {
        entity.setNome(dto.getNome());
        entity.setCapa(dto.getCapa());
        entity.setLinkLivros(dto.getLinkLivros());
        DiscProfTurma discProfTurma = discProfTurmaRepository.findById(dto.getDiscprofturma_id())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        entity.setDiscProfTurma(discProfTurma);
    }
}
