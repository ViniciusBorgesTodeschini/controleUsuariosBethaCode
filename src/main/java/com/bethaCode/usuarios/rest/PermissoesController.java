package com.bethaCode.usuarios.rest;

import com.bethaCode.usuarios.model.dto.PermissoesDTO;
import com.bethaCode.usuarios.model.entity.ModuloSoftware;
import com.bethaCode.usuarios.model.entity.Permissoes;
import com.bethaCode.usuarios.model.entity.Usuario;
import com.bethaCode.usuarios.model.repository.ModuloSoftwareRepository;
import com.bethaCode.usuarios.model.repository.PermissoesRepository;
import com.bethaCode.usuarios.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/permissoes")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class PermissoesController {
    private final ModuloSoftwareRepository moduloSoftwareRepository;
    private final UsuarioRepository usuarioRepository;
    private final PermissoesRepository permissoesRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Permissoes salvarPermissoes(@RequestBody PermissoesDTO permissoesDTO){
        Integer idUsuario = permissoesDTO.getIdUsuario();
        Usuario usuario = usuarioRepository
                        .findById(idUsuario)
                        .orElseThrow(()-> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Usuário " + idUsuario + " não localizado na aplicação!"));

        Integer idModulo = permissoesDTO.getIdModuloSoftware();
        ModuloSoftware moduloSoftware = moduloSoftwareRepository
                        .findById(idModulo)
                        .orElseThrow(()-> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Módulo do software" + idModulo + " não localizado na aplicação!"));

        Permissoes permissoes = new Permissoes();
        permissoes.setNome(permissoesDTO.getNome());
        permissoes.setUsuario(usuario);
        permissoes.setModuloSoftware(moduloSoftware);
        return permissoesRepository.save(permissoes);
    }

    @GetMapping
    public List<Permissoes> pesquisar(
            @RequestParam(value  = "nome", required = false, defaultValue = "")String nome){
        return permissoesRepository.findByNomeUsuario("%" + nome + "%");
    }

    @GetMapping("{id}")
    public Permissoes buscarPermissoes(@PathVariable Integer id){
        return permissoesRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Permissão " + id + " não localizada na aplicação!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPermissoes(@PathVariable Integer id){
        permissoesRepository
                .findById(id)
                .map(permissoes -> {
                    permissoesRepository.delete(permissoes);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Permissão " + id + " não localizada na aplicação!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPermissoes(@PathVariable Integer id, @RequestBody PermissoesDTO permissoesDTO){
        Integer idUsuario = permissoesDTO.getIdUsuario();
        Usuario usuario = usuarioRepository
                .findById(idUsuario)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Usuário " + idUsuario + " não localizado na aplicação!"));

        Integer idModulo = permissoesDTO.getIdModuloSoftware();
        ModuloSoftware moduloSoftware = moduloSoftwareRepository
                .findById(idUsuario)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Módulo " + idModulo + " não localizado na aplicação!"));

        permissoesRepository
                .findById(id)
                .map(permissoes -> {
                    permissoes.setNome(permissoesDTO.getNome());
                    permissoes.setUsuario(usuario);
                    permissoes.setModuloSoftware(moduloSoftware);
                    return permissoesRepository.save(permissoes);
                })
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Permissão " + id + " não localizada na aplicação!"));
    }
}
