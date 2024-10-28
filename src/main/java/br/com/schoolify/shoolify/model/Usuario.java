package br.com.schoolify.shoolify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "tb_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    @Column(unique = true)
    private String telefone;
    @NotNull
    private String senha;


    @OneToMany (mappedBy = "usuario")
    private List<DiscProfTurma> discProfTurmas = new ArrayList<>();

    @OneToMany (mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany (mappedBy = "usuario")
    private List<Evento> eventos = new ArrayList<>();

    @ManyToOne
    @JoinColumn (name = "tipousuario_id")
    private TipoUsuario tipoUsuario;

    @ManyToMany
    @JoinTable(name = "tb_usuario_turmapai",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "turmapai_id"))
    private Set<TurmaPai> turmaPais = new HashSet<>();
}
