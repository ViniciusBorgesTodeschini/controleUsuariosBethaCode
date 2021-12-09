package com.bethaCode.usuarios.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PermissoesDTO {
    private String nome;
    private Integer idUsuario;
    private Integer IdModuloSoftware;

    public PermissoesDTO(){

    }
}
