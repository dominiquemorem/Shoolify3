package br.com.schoolify.shoolify.repository;


import br.com.schoolify.shoolify.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
}