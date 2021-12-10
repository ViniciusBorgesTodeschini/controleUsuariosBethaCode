package com.bethaCode.usuarios.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class ModuloSoftware {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "O campo nome deve ser informado!")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotEmpty(message = "O campo nome do software deve ser informado!")
    @Column(nullable = false, length = 100, name = "nome_software")
    private String nomeSoftware;

}
