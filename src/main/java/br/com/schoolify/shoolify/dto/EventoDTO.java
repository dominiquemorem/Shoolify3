package br.com.schoolify.shoolify.dto;

import br.com.schoolify.shoolify.model.Evento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;


public class EventoDTO {
    private Long id;

    @Size(min = 10, max = 80, message = "Titulo precisa ter de 10 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String nome;

    private LocalDate dataInicio;
    private LocalTime horaInicio;

    @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
    @NotBlank(message = "Campo requerido")
    private String descricao;

    public EventoDTO(Long id, String nome, LocalDate dataInicio, LocalTime horaInicio, String descricao) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.descricao = descricao;
    }

    public EventoDTO(Evento entity) {
        id = entity.getId();
        nome = entity.getNome();
        dataInicio = entity.getDataInicio();
        horaInicio = entity.getHoraInicio();
        descricao = entity.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public String getDescricao() {
        return descricao;
    }
}
