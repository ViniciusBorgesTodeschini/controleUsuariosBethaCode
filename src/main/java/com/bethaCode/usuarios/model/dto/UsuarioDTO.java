package com.bethaCode.usuarios.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String cpf;
    private String nomeUsuario;
    //private String senha;
    private String funcao;
    private String setor;
    private String empresa;
    private Boolean alteraDados;
    private Boolean alteraSenha;
    private Boolean alteraLogin;
    private Boolean redefineSenha;

    public UsuarioDTO(){

    }
}
