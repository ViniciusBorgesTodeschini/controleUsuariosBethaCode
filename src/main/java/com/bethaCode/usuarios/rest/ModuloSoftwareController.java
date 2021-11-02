package com.bethaCode.usuarios.rest;

import com.bethaCode.usuarios.model.dto.ModuloSoftwareDTO;
import com.bethaCode.usuarios.model.dto.SoftwareDTO;
import com.bethaCode.usuarios.model.entity.Empresa;
import com.bethaCode.usuarios.model.entity.ModuloSoftware;
import com.bethaCode.usuarios.model.entity.Software;
import com.bethaCode.usuarios.model.repository.ModuloSoftwareRepository;
import com.bethaCode.usuarios.model.repository.SoftwareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/modulo-software")
@RequiredArgsConstructor
public class ModuloSoftwareController {
    private final SoftwareRepository softwareRepository;
    private final ModuloSoftwareRepository moduloSoftwareRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModuloSoftware salvarModuloSoftware(@RequestBody ModuloSoftwareDTO moduloSoftwareDTO){
        Integer idSoftware = moduloSoftwareDTO.getIdSoftware();
        Software software = softwareRepository
                        .findById(idSoftware)
                        .orElseThrow(()-> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Software " + idSoftware + " não localizado na aplicação!"));

        ModuloSoftware moduloSoftware = new ModuloSoftware();
        moduloSoftware.setNome(moduloSoftwareDTO.getNome());
        moduloSoftware.setSoftware(software);
        return moduloSoftwareRepository.save(moduloSoftware);
    }

    @GetMapping("{id}")
    public ModuloSoftware buscarModuloSoftware(@PathVariable Integer id){
        return moduloSoftwareRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Módulo " + id + " não localizada na aplicação!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarModuloSoftware(@PathVariable Integer id){
        moduloSoftwareRepository
                .findById(id)
                .map (moduloSoftware -> {
                    moduloSoftwareRepository.delete(moduloSoftware);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Módulo " + id + " não localizada na aplicação!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarModuloSoftware(@PathVariable Integer id, @RequestBody ModuloSoftwareDTO moduloSoftwareDTO){
        Integer idSoftware = moduloSoftwareDTO.getIdSoftware();
        Software software = softwareRepository
                .findById(idSoftware)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Software " + idSoftware + " não localizado na aplicação!"));

        moduloSoftwareRepository
                .findById(id)
                .map(moduloSoftware -> {
                    moduloSoftware.setNome(moduloSoftwareDTO.getNome());
                    moduloSoftware.setSoftware(software);
                    return moduloSoftwareRepository.save(moduloSoftware);
                })
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Módulo " + id + " não localizada na aplicação!"));
    }
}
