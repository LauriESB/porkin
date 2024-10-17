package com.porkin.repository;

import com.porkin.entity.DespesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<DespesaEntity, Long> {
}
