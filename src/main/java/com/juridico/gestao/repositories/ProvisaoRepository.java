package com.juridico.gestao.repositories;

import com.juridico.gestao.Entity.Provisao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvisaoRepository extends JpaRepository<Provisao, Integer> {
    Provisao findByNumeroCnj(String numeroCnj);
}
