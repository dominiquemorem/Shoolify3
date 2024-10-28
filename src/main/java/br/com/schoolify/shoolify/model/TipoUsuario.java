package br.com.schoolify.shoolify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_tipo_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoUsuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private ClasseUsuario tipoUsuario;

    @OneToMany (mappedBy = "tipoUsuario")
    private List<Usuario> usuarios = new ArrayList<>();
}
