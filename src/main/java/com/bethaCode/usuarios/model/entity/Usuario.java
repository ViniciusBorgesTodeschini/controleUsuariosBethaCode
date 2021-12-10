package com.bethaCode.usuarios.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "O campo nome deve ser informado!")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotEmpty(message = "O campo cpf deve ser informado!")
    @Column
    private String cpf;

    @NotEmpty(message = "O campo nome de usuário deve ser informado!")
    @Column(nullable = false, length = 20, name = "nome_usuario")
    private String nomeUsuario;

    @Column(nullable = false, length = 20)
    private String senha;

    @Column(nullable = false, length = 50)
    private String funcao;

    @Column(nullable = false, length = 50)
    private String setor;

    @NotEmpty(message = "O campo empresa deve ser informado!")
    @Column(nullable = false, length = 100)
    private String empresa;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    String senhaPadrao = "senha123";

    @PrePersist
    public void prePersiste(){
        setDataCadastro(LocalDate.now());
        setSenha(this.getSenhaPadrao());
    }
}
