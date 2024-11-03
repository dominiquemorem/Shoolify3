package br.com.schoolify.shoolify.dto;

import br.com.schoolify.shoolify.model.Turma;
import jakarta.validation.constraints.NotBlank;


public class TurmaDTO {
    private Long id;

    @NotBlank(message = "Campo requerido")
    private String descricao;

    public TurmaDTO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public TurmaDTO(Turma entity) {
        id = entity.getId();
        descricao = entity.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
