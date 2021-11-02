package com.bethaCode.usuarios.rest;

import com.bethaCode.usuarios.model.entity.Empresa;
import com.bethaCode.usuarios.model.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

    private final EmpresaRepository repository;

    @Autowired
    public EmpresaController(EmpresaRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa salvarEmpresa(@Valid @RequestBody Empresa empresa){
        return  repository.save(empresa);
    }

    @GetMapping("{id}")
    public Empresa buscarEmpresa(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi possível localizar a empresa " + id + "!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEmpresa(@PathVariable Integer id){
        repository
                .findById(id)
                .map(empresa ->{
                    repository.delete(empresa);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi possível localizar a empresa " + id + "!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarEmpresa(@PathVariable Integer id, @Valid @RequestBody Empresa dadoAtualizado){
        repository
                .findById(id)
                .map(empresa ->{
                    empresa.setRazaoSocial(dadoAtualizado.getRazaoSocial());
                    empresa.setNomeFantasia(dadoAtualizado.getNomeFantasia());
                    empresa.setCnpj(dadoAtualizado.getCnpj());
                    empresa.setEndereco(dadoAtualizado.getEndereco());
                    empresa.setBairro(dadoAtualizado.getBairro());
                    empresa.setCidade(dadoAtualizado.getCidade());
                    empresa.setUf(dadoAtualizado.getUf());
                    return repository.save(empresa);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi possível localizar a empresa " + id + "!"));
    }
}
