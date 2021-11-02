package com.bethaCode.usuarios.rest;

import com.bethaCode.usuarios.model.entity.Usuario;
import com.bethaCode.usuarios.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioController(UsuarioRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvarUsuario(@Valid @RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    @GetMapping("{id}")
    public Usuario buscarUsuario(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi possível localizar o usuário " + id + "!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Integer id){
        repository
                .findById(id)
                .map(usuario ->{
                    repository.delete(usuario);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi possível localizar o usuário " + id + "!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario dadoAtualizado){
        repository
                .findById(id)
                .map(usuario -> {
                    usuario.setNome(dadoAtualizado.getNome());
                    usuario.setCpf(dadoAtualizado.getCpf());
                    usuario.setNomeUsuario(dadoAtualizado.getNomeUsuario());
                    usuario.setSenha(dadoAtualizado.getSenha());
                    usuario.setFuncao(dadoAtualizado.getFuncao());
                    usuario.setSetor(dadoAtualizado.getSetor());
                    usuario.setEmpresa(dadoAtualizado.getEmpresa());
                    return repository.save(usuario);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi possível localizar o usuário " + id + "!"));
    }

    /*//Esse altera para a senha desejada pelo usuario
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Integer id, @Valid @RequestBody Usuario dadoAtualizado){
        repository
                .findById(id)
                .map(usuario -> {
                    usuario.setSenha(dadoAtualizado.getSenha());
                    return repository.save(usuario);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi possível localizar o usuário " + id + "!"));
    }

    //Esse volta para a senha padrão de sistema
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void redefinirSenha(@PathVariable Integer id){
        repository
                .findById(id)
                .map(usuario -> {
                    usuario.setSenha(usuario.getSenhaPadrao());
                    return repository.save(usuario);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi possível localizar o usuário " + id + "!"));
    }*/
}
