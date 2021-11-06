package com.bethaCode.usuarios.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Software {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "O campo nome deve ser informado!")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotEmpty(message = "O campo tipo de lisença deve ser informado!")
    @Column(nullable = false, length = 20, name = "tipo_licenca")
    private String tipoLicenca;

    @ManyToOne
    @NotEmpty(message = "O campo empresa deve ser informado!")
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    /*@PrePersist
    public void prePersiste(){
        //Betha
        setNome("Linha Cloud");
        setTipoLicenca("Proprietária");

        setNome("SAPO");
        setTipoLicenca("Proprietária");

        //Outras
        setNome("Outro software qualquer");
        setTipoLicenca("Proprietária");

        setNome("Software sem importância");
        setTipoLicenca("Proprietária");
    }*/
}
