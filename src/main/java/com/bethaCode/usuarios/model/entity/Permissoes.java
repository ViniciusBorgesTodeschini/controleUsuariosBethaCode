package com.bethaCode.usuarios.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Permissoes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "O campo nome deve ser informado!")
    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne
    //@NotEmpty(message = "O campo usuário deve ser informado!")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    //@NotEmpty(message = "O campo módulo deve ser informado!")
    @JoinColumn(name = "id_modulo_software")
    private ModuloSoftware moduloSoftware;
}
