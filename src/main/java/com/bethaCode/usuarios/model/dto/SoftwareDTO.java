package com.bethaCode.usuarios.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class SoftwareDTO {
    private String nome;
    private String tipoLicenca;
    private Integer idEmpresa;

    public SoftwareDTO(){

    }
}
