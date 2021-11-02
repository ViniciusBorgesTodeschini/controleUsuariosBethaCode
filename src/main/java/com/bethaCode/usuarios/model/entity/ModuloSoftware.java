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

    @NotEmpty(message = "O campo software deve ser informado!")
    @JoinColumn(name = "id_software")
    private Software software;

    @PrePersist
    public void prePersiste(){
        //Betha
        setNome("Contábil");

        setNome("Tesouraria");

        setNome("Fly-e Nota");

        setNome("Livro eletrônico");

        //Outras
        setNome("Contábil");

        setNome("Financeiro");
    }
}
