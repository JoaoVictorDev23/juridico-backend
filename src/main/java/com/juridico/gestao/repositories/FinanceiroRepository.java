package com.juridico.gestao.repositories;

import com.juridico.gestao.Entity.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceiroRepository extends JpaRepository<Financeiro, Integer> {
    Financeiro findByNumeroCnj(String numeroCnj);
}
