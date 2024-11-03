package br.com.schoolify.shoolify.services;

import br.com.schoolify.shoolify.dto.AtividadeDTO;
import br.com.schoolify.shoolify.dto.ComentarioDTO;
import br.com.schoolify.shoolify.model.Atividade;
import br.com.schoolify.shoolify.model.Comentario;
import br.com.schoolify.shoolify.model.Usuario;
import br.com.schoolify.shoolify.repository.AtividadeRepository;
import br.com.schoolify.shoolify.repository.ComentarioRepository;
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
public class ComentarioService {

    @Autowired
    private ComentarioRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Transactional(readOnly = true)
    public ComentarioDTO findById(Long id) {
        Comentario comentario = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ComentarioDTO(comentario);
    }

    @Transactional(readOnly = true)
    public Page<ComentarioDTO> findAll(Pageable pageable) {
        Page<Comentario> result = repository.findAll(pageable);
        return result.map(x -> new ComentarioDTO(x));
    }

    @Transactional
    public ComentarioDTO insert(ComentarioDTO dto) {
        Comentario entity = new Comentario();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ComentarioDTO(entity);
    }

    @Transactional
    public ComentarioDTO update(Long id, ComentarioDTO dto) {
        try {
            Comentario entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ComentarioDTO(entity);
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

    private void copyDtoToEntity(ComentarioDTO dto, Comentario entity) {
        entity.setConteudo(dto.getConteudo());
        entity.setDataHora(dto.getDataHora());
        Usuario usuario = usuarioRepository.findById(dto.getUsuario_id())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        entity.setUsuario(usuario);
        Atividade atividade = atividadeRepository.findById(dto.getAtividade_id())
                .orElseThrow(() -> new ResourceNotFoundException("Atividade não encontrada"));
        entity.setAtividade(atividade);
    }
}
