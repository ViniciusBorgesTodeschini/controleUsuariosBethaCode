package com.bethaCode.usuarios.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class EmpresaDTO {
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;

    public EmpresaDTO(){

    }
}
