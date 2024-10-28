package br.com.schoolify.shoolify.repository;

import br.com.schoolify.shoolify.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
}