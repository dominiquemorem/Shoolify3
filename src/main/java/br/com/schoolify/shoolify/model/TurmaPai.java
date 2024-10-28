package br.com.schoolify.shoolify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_turmapai")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaPai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "turmaPais")
    private Set<Usuario> usuarios = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tb_turmapai_turma",
            joinColumns = @JoinColumn(name = "turmapai_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id"))
    private Set<Turma> turmas = new HashSet<>();
    
}
