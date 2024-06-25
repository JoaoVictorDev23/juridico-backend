package com.juridico.gestao.services;

import com.juridico.gestao.DTO.AnotacaoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnotacaoService {
    List<AnotacaoDTO> findByNumeroCnj(String nfd);
    void createAnotacao(AnotacaoDTO anotacaoDTO);
}
