package br.com.schoolify.shoolify.dto;

import br.com.schoolify.shoolify.model.ClasseUsuario;
import br.com.schoolify.shoolify.model.TipoUsuario;


public class TipoUsuarioDTO {

    private Long id;

    private ClasseUsuario tipoUsuario;

    public TipoUsuarioDTO(Long id, ClasseUsuario tipoUsuario) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
    }

    public TipoUsuarioDTO(TipoUsuario entity) {
        id = entity.getId();
        tipoUsuario = entity.getTipoUsuario();
    }

    public Long getId() {
        return id;
    }

    public ClasseUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}
