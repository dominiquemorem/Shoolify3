package br.com.schoolify.shoolify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_discprofturma")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscProfTurma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany (mappedBy = "discProfTurma")
    private List<SugestaoLivro> sugestaoLivros = new ArrayList<>();

    @OneToMany (mappedBy = "discProfTurma")
    private List<Atividade> atividades = new ArrayList<>();

    @ManyToOne
    @JoinColumn (name = "usuario_id")
    private Usuario usuario;

    
    @ManyToMany
    @JoinTable(name = "tb_discprofturma_turma",
            joinColumns = @JoinColumn(name = "discprofturma_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id"))
    private Set<Turma> turmas = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tb_discprofturma_disciplina",
            joinColumns = @JoinColumn(name = "discprofturma_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
    private Set<Disciplina> disciplinas = new HashSet<>();

}
