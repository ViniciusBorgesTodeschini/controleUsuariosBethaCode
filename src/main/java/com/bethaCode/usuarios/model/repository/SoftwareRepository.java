package com.bethaCode.usuarios.model.repository;

import com.bethaCode.usuarios.model.entity.Software;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareRepository extends JpaRepository<Software, Integer> {
}
