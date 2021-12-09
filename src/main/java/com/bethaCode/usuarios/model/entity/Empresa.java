package com.bethaCode.usuarios.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "O campo Razão Social deve ser informado!")
    @Column(nullable = false, length = 100, name = "razao_social")
    private String razaoSocial;

    @NotEmpty(message = "O campo Nome Fantasia deve ser informado!")
    @Column(nullable = false, length = 100, name = "nome_fantasia")
    private String nomeFantasia;

    @NotEmpty(message = "O campo cnpj deve ser informado!")
    @Column
    private String cnpj;

    @NotEmpty(message = "O campo endereço deve ser informado!")
    @Column
    private String endereco;

    @NotEmpty(message = "O campo bairro deve ser informado!")
    @Column
    private String bairro;

    @NotEmpty(message = "O campo cidade deve ser informado!")
    @Column
    private String cidade;

    @NotEmpty(message = "O campo UF deve ser informado!")
    @Column
    private String uf;

    /*@PrePersist
    public void prePersiste(){
        setRazaoSocial("Betha Sistemas Ltda.");
        setNomeFantasia("Betha Sistemas - Matriz");
        setCnpj("00.000.000/0001-00");
        setEndereco("Rua A, 222");
        setBairro("Centro");
        setCidade("Criciúma");
        setUf("SC");

        setRazaoSocial("Outra Empresa de Software Ltda.");
        setNomeFantasia("Empresa B");
        setCnpj("00.000.000/0001-01");
        setEndereco("Rua B, 123");
        setBairro("Centro");
        setCidade("São Paulo");
        setUf("SP");
    }*/

}
