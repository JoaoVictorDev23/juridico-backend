package com.juridico.gestao.repositories;

import com.juridico.gestao.Entity.Extrajudicial;
import com.juridico.gestao.Entity.ParcelasExtrajudicial;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface ParcelasExtrajudicialRepository extends JpaRepository<ParcelasExtrajudicial, Integer> {

    // Método para deletar parcelas pelo ID do extrajudicial
    @Modifying
    @Transactional
    void deleteByExtrajudicial(Extrajudicial extrajudicial);

    List<ParcelasExtrajudicial> findByExtrajudicialId(Integer extrajudicialId);
}
