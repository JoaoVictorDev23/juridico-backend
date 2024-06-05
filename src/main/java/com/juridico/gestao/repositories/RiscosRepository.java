package com.juridico.gestao.repositories;

import com.juridico.gestao.Entity.Provisao;
import com.juridico.gestao.Entity.Riscos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiscosRepository extends JpaRepository<Riscos, Long> {
    Riscos findByNumeroCnj(Long numeroCnj);
}
