package br.com.schoolify.shoolify.repository;

import br.com.schoolify.shoolify.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}