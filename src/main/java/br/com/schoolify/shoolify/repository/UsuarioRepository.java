package br.com.schoolify.shoolify.repository;

import br.com.schoolify.shoolify.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}