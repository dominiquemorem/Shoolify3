package br.com.schoolify.shoolify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table (name = "tb_evento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private LocalDate dataInicio;
    private LocalTime horaInicio;
    @NotNull
    @Column (columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn (name = "usuario_id")
    private Usuario usuario;
}
