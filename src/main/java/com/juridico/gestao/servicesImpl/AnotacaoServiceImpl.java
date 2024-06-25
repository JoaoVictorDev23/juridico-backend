package com.juridico.gestao.servicesImpl;

import com.juridico.gestao.DTO.AnotacaoDTO;
import com.juridico.gestao.Entity.Anotacao;
import com.juridico.gestao.repositories.AnotacaoRepository;
import com.juridico.gestao.services.AnotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnotacaoServiceImpl implements AnotacaoService {

    @Autowired
    AnotacaoRepository anotacaoRepository;

    @Override
    public void createAnotacao(AnotacaoDTO anotacaoDTO) {
        // Obter a data e hora atuais
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        Anotacao anotacao = new Anotacao(anotacaoDTO);
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        anotacao.setUserVinculado(emailUsuario);
        // Atribuir a data e hora atuais ao objeto Anotacao
        anotacao.setDatahora(dataHoraAtual);

        anotacaoRepository.save(anotacao);

    }

    @Override
    public List<AnotacaoDTO> findByNumeroCnj(String numeroCnj) {
        List<Anotacao> anotacaos = anotacaoRepository.findByNumeroCnjVinculado(numeroCnj);
        return anotacaos.stream()
                .map(AnotacaoDTO::new)
                .collect(Collectors.toList());
    }
}
