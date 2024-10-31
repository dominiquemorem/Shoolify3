package br.com.schoolify.shoolify.repository;

import br.com.schoolify.shoolify.model.SugestaoLivro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SugestaoLivroRepository extends JpaRepository<SugestaoLivro, Long> {
}