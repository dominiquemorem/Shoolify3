package br.com.schoolify.shoolify.repository;

import br.com.schoolify.shoolify.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}