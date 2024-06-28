package com.juridico.gestao.repositories;

import com.juridico.gestao.Entity.Extrajudicial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtrajudicialRepository extends JpaRepository<Extrajudicial, Integer> {
    Extrajudicial findByPasta(String pasta);
}
