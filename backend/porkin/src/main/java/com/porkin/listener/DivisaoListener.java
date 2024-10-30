package com.porkin.listener;

import com.porkin.compositekeys.DivisaoIDs;
import com.porkin.entity.AmizadeEntity;
import com.porkin.entity.DespesaEntity;
import com.porkin.entity.DivisaoEntity;
import com.porkin.repository.AmizadeRepository;
import com.porkin.repository.DivisaoRepository;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DivisaoListener {

  @Autowired
  private DivisaoRepository divisaoRepository;

  @Autowired
  private AmizadeRepository amizadeRepository;

  @PostPersist
  public void onDespesaCreated(DespesaEntity despesaEntity) {
    Long idUsuarioCriador = despesaEntity.getUsuarioCriador().getId();
    List<AmizadeEntity> amizades = amizadeRepository.findByIdUsuario(idUsuarioCriador);

    amizades.forEach(amizade -> {

      DivisaoIDs idsDivisao = new DivisaoIDs();
      idsDivisao.setIdDespesa(despesaEntity.getId());
      idsDivisao.setIdAmigo(despesaEntity.getId());

      DivisaoEntity divisao = new DivisaoEntity();
      divisao.setDivisaoIDs(idsDivisao);
      divisao.setIdUsuarioCriador(despesaEntity.getUsuarioCriador());
      divisao.setPago(false);

      divisaoRepository.save(divisao);
    });

  }

}
