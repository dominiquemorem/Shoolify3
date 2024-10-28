package br.com.schoolify.shoolify.repository;

import br.com.schoolify.shoolify.model.SugestaoLivro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SugestoesLivrosRepository extends JpaRepository<SugestaoLivro, Long> {
}