package com.bethaCode.usuarios.model.repository;

import com.bethaCode.usuarios.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
