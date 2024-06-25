package com.juridico.gestao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.juridico.gestao.Entity.Riscos;
import com.juridico.gestao.repositories.RiscosRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
public class RiscosRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(RiscosRepositoryTest.class);

    @Autowired
    private RiscosRepository riscosRepository;

    @Test
    @Transactional
    public void testFindByNumeroCnj() {
        String numeroCnj = "654321";

        // Primeiro, salve um risco no banco de dados de teste
        Riscos risco = new Riscos();
        risco.setNumeroCnj(numeroCnj);
        risco.setRiscoEstimado(12312300.00);
        risco.setRiscoMaximo(5000.00);
        risco.setRiscoMinimo(500.00);
        risco.setRiscoPossivel(2000.00);
        risco.setRiscoProvavel(1500.00);
        risco.setRiscoRemoto(300.00);

        riscosRepository.saveAndFlush(risco);

        // Agora, tente encontrar pelo numeroCnj
        Riscos foundRisco = riscosRepository.findByNumeroCnj(numeroCnj);
        assertNotNull(foundRisco, "Risco não encontrado!");

        logger.info("Found Risco: " + foundRisco);
        assertEquals(numeroCnj, foundRisco.getNumeroCnj(), "Número CNJ não corresponde!");
    }
}