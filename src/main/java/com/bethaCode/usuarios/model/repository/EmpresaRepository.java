package com.bethaCode.usuarios.model.repository;

import com.bethaCode.usuarios.model.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
}
