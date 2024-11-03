package br.com.schoolify.shoolify.dto;

import br.com.schoolify.shoolify.model.DiscProfTurma;
import br.com.schoolify.shoolify.model.SugestaoLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class SugestaoLivroDTO {

    private Long id;

    @Size(min = 10, max = 80, message = "O nome do livro precisa ter de 10 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String nome;
    private String capa;
    private String linkLivros;
    private Long discprofturma_id;

    public SugestaoLivroDTO(Long id, String nome, String capa, String linkLivros, Long discprofturma_id) {
        this.id = id;
        this.nome = nome;
        this.capa = capa;
        this.linkLivros = linkLivros;
        this.discprofturma_id = discprofturma_id;
    }

    public SugestaoLivroDTO(SugestaoLivro entity) {
        id = entity.getId();
        nome = entity.getNome();
        capa = entity.getCapa();
        linkLivros = entity.getLinkLivros();
        discprofturma_id = entity.getDiscProfTurma().getId();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCapa() {
        return capa;
    }

    public String getLinkLivros() {
        return linkLivros;
    }

    public Long getDiscprofturma_id() {
        return discprofturma_id;
    }
}
