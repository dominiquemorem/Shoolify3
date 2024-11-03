package br.com.schoolify.shoolify.dto;

import br.com.schoolify.shoolify.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UsuarioDTO {
    private Long id;

    @Size(min = 10, max = 80, message = "O nome precisa ter entre 10 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String nome;
    @NotBlank(message = "Campo requerido")
    private String email;
    @NotBlank(message = "Campo requerido")
    private String telefone;
    @NotBlank(message = "Campo requerido")
    private String senha;


    public UsuarioDTO(Long id, String nome, String email, String telefone, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public UsuarioDTO(Usuario entity) {
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        telefone = entity.getTelefone();
        senha = entity.getSenha();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSenha() { return senha;}
}
