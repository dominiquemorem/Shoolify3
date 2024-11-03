package br.com.schoolify.shoolify.dto;

import br.com.schoolify.shoolify.model.Disciplina;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class DisciplinaDTO {
    private Long id;
    @Size(min = 4, max = 80, message = "Titulo precisa ter de 4 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String nome;
    @NotBlank(message = "Campo requerido")
    private String imgUrl;


    public DisciplinaDTO(Long id, String nome, String imgUrl) {
        this.id = id;
        this.nome = nome;
        this.imgUrl = imgUrl;
    }

    public DisciplinaDTO(Disciplina entity) {
        id = entity.getId();
        nome = entity.getNome();
        imgUrl = entity.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public String getImgUrl() {
        return imgUrl;
    }


}
