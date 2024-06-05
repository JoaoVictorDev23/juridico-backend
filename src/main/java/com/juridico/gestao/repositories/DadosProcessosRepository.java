package com.juridico.gestao.repositories;

import com.juridico.gestao.Entity.Dadosprocesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosProcessosRepository extends JpaRepository<Dadosprocesso, Integer> {

    Dadosprocesso findByNumeroCnj(Integer dadosProcesso);
}
