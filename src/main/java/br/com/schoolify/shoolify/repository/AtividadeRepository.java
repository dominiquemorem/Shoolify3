package br.com.schoolify.shoolify.repository;

import br.com.schoolify.shoolify.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
