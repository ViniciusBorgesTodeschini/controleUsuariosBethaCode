package com.bethaCode.usuarios.rest;

import com.bethaCode.usuarios.model.dto.ModuloSoftwareDTO;
import com.bethaCode.usuarios.model.entity.ModuloSoftware;
import com.bethaCode.usuarios.model.repository.ModuloSoftwareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/modulo-software")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class ModuloSoftwareController {
    private final ModuloSoftwareRepository moduloSoftwareRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModuloSoftware salvarModuloSoftware(@RequestBody ModuloSoftwareDTO moduloSoftwareDTO){

        ModuloSoftware moduloSoftware = new ModuloSoftware();
        moduloSoftware.setNome(moduloSoftwareDTO.getNome());
        moduloSoftware.setNomeSoftware(moduloSoftwareDTO.getNomeSoftware());
        return moduloSoftwareRepository.save(moduloSoftware);
    }

    @GetMapping
    public List<ModuloSoftware> acharTodos(){
        return moduloSoftwareRepository.findAll();
    }

    @GetMapping("{id}")
    public ModuloSoftware buscarModuloSoftware(@PathVariable Integer id){
        return moduloSoftwareRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Módulo do software" + id + " não localizada na aplicação!"));
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

        moduloSoftwareRepository
                .findById(id)
                .map(moduloSoftware -> {
                    moduloSoftware.setNome(moduloSoftwareDTO.getNome());
                    moduloSoftware.setNomeSoftware(moduloSoftwareDTO.getNomeSoftware());
                    return moduloSoftwareRepository.save(moduloSoftware);
                })
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Módulo " + id + " não localizada na aplicação!"));
    }
}
