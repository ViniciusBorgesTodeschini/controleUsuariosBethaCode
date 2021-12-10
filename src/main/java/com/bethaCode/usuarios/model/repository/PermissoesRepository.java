package com.bethaCode.usuarios.model.repository;

import com.bethaCode.usuarios.model.entity.Permissoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissoesRepository extends JpaRepository<Permissoes, Integer> {

    @Query("select p from Permissoes p join p.usuario u where upper (u.nome) like upper (:nome)")
    List<Permissoes> findByNomeUsuario(@Param("nome") String nome);
}
