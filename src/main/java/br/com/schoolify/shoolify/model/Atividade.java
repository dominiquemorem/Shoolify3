package br.com.schoolify.shoolify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tb_atividade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataEntrega;
    @Column (columnDefinition = "TEXT")
    private String descricao;

    @OneToMany (mappedBy = "atividade")
    private List<Comentario> comentarios = new ArrayList<>();

    @ManyToOne
    @JoinColumn (name = "discprofturma_id")
    private DiscProfTurma discProfTurma;
}
