package com.bethaCode.usuarios.rest;

import com.bethaCode.usuarios.model.dto.SoftwareDTO;
import com.bethaCode.usuarios.model.entity.Empresa;
import com.bethaCode.usuarios.model.entity.Software;
import com.bethaCode.usuarios.model.repository.EmpresaRepository;
import com.bethaCode.usuarios.model.repository.SoftwareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/software")
@RequiredArgsConstructor
public class SoftwareController {
    private final SoftwareRepository softwareRepository;
    private final EmpresaRepository empresaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Software salvarSoftware(@RequestBody SoftwareDTO softwareDTO){
        Integer idEmpresa = softwareDTO.getIdEmpresa();
        Empresa empresa = empresaRepository
                        .findById(idEmpresa)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                       "Empresa " + idEmpresa + " não localizada na aplicação!"));

        Software software = new Software();
        software.setNome(softwareDTO.getNome());
        software.setTipoLicenca(softwareDTO.getTipoLicenca());
        software.setEmpresa(empresa);
        return softwareRepository.save(software);
    }

    @GetMapping("{id}")
    public Software buscarSoftware(@PathVariable Integer id){
        return softwareRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Software " + id + " não localizada na aplicação!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarSoftware(@PathVariable Integer id){
        softwareRepository
                .findById(id)
                .map( software -> {
                        softwareRepository.delete(software);
                            return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Software " + id + " não localizado na aplicação!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizaSoftware(@PathVariable Integer id, @RequestBody SoftwareDTO softwareDTO){
        Integer idEmpresa = softwareDTO.getIdEmpresa();
        Empresa empresa = empresaRepository
                .findById(idEmpresa)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Empresa " + idEmpresa + " não localizada na aplicação!"));

        softwareRepository
                .findById(id)
                .map(software -> {
                    software.setNome(softwareDTO.getNome());
                    software.setTipoLicenca(softwareDTO.getTipoLicenca());
                    software.setEmpresa(empresa);
                    return softwareRepository.save(software);
                })
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Software " + id + " não localizada na aplicação!"));
    }
}
