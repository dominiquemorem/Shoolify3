package br.com.schoolify.shoolify.dto;

import br.com.schoolify.shoolify.model.DiscProfTurma;
import br.com.schoolify.shoolify.model.Usuario;


public class DiscProfTurmaDTO {

    private Long id;
    private Usuario usuario;

    public DiscProfTurmaDTO(Long id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public DiscProfTurmaDTO(DiscProfTurma entity) {
        id = entity.getId();
        usuario = entity.getUsuario();
    }

    public Long getId() {
        return id;
    }

    public Long getUsuario() {
        return usuario.getId();
    }
}
