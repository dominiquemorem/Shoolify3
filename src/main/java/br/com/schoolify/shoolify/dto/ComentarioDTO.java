package br.com.schoolify.shoolify.dto;

import br.com.schoolify.shoolify.model.Comentario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public class ComentarioDTO {

    private Long id;
    @Size(min = 10, max = 800, message = "Titulo precisa ter de 10 a 800 caracteres")
    @NotBlank(message = "Campo requerido")
    private String conteudo;
    private LocalDateTime dataHora;
    private Long usuario_id;
    private Long atividade_id;

    public ComentarioDTO(Long id, String conteudo, LocalDateTime dataHora, Long usuario_id, Long atividade_id) {
        this.id = id;
        this.conteudo = conteudo;
        this.dataHora = dataHora;
        this.usuario_id = usuario_id;
        this.atividade_id = atividade_id;
    }

    public ComentarioDTO(Comentario entity) {
        id = entity.getId();
        conteudo = entity.getConteudo();
        dataHora = entity.getDataHora();
        usuario_id = entity.getUsuario().getId();
        atividade_id = entity.getAtividade().getId();

    }

    public Long getId() {
        return id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public Long getAtividade_id() {
        return atividade_id;
    }

}
