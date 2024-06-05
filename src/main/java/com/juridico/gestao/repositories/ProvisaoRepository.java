package com.juridico.gestao.repositories;

import com.juridico.gestao.Entity.Provisao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvisaoRepository extends JpaRepository<Provisao, Long> {
    Provisao findByNumeroCnj(Long numeroCnj);
}
