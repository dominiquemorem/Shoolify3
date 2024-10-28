package br.com.schoolify.shoolify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table (name = "tb_comentario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column (columnDefinition = "TEXT")
    private String conteudo;
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn (name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn (name = "atividade_id")
    private Atividade atividade;
}
