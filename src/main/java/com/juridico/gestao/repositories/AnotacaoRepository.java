package com.juridico.gestao.repositories;

import com.juridico.gestao.Entity.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {

    List<Anotacao> findByNumeroCnjVinculado(String numeroCnj);
}
