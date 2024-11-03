package br.com.schoolify.shoolify.dto;

import br.com.schoolify.shoolify.model.Atividade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;


public class AtividadeDTO {
    private Long id;

    @Size(min = 10, max = 80, message = "Titulo precisa ter de 10 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String titulo;

    private LocalDateTime dataInicio;
    private LocalDateTime dataEntrega;

    @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
    @NotBlank(message = "Campo requerido")
    private String descricao;

    private Long discprofturma_id;

    public AtividadeDTO(Long id, String titulo, LocalDateTime dataInicio, LocalDateTime dataEntrega, String descricao, Long discprofturma_id) {
        this.id = id;
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataEntrega = dataEntrega;
        this.descricao = descricao;
        this.discprofturma_id = discprofturma_id;
    }

    public AtividadeDTO(Atividade entity) {
        id = entity.getId();
        titulo = entity.getTitulo();
        dataInicio = entity.getDataInicio();
        dataEntrega = entity.getDataEntrega();
        descricao = entity.getDescricao();
        discprofturma_id = entity.getDiscProfTurma().getId();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getDiscprofturma_id() {
        return discprofturma_id;
    }
}
