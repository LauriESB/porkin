package com.porkin.repository;

import com.porkin.entity.AmizadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AmizadeRepository extends JpaRepository<AmizadeEntity, Long> {
  @Query("select a from AmizadeEntity a where a.amizadeIDs.idUsuario = :idUsuario")
  List<AmizadeEntity> findByIdUsuario(@Param("idUsuario") Long idUsuario);
}
