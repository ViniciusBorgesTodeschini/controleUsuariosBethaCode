package com.bethaCode.usuarios.model.repository;

import com.bethaCode.usuarios.model.entity.Permissoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissoesRepository extends JpaRepository<Permissoes, Integer> {
}
