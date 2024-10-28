package br.com.schoolify.shoolify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_turma")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turma {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String descricao;

    @ManyToMany(mappedBy = "turmas")
    private Set<DiscProfTurma> discProfTurmas = new HashSet<>();

    @ManyToMany(mappedBy = "turmas")
    private Set<TurmaPai> turmaPais = new HashSet<>();
}
