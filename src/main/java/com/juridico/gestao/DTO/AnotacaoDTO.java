package com.juridico.gestao.DTO;

import com.juridico.gestao.Entity.Anotacao;

import java.time.LocalDateTime;

public record AnotacaoDTO
        (
        String message,
        String usuario,
        String numeroCnjVinculado,
        LocalDateTime datahora
) {

public AnotacaoDTO(Anotacao anotacao){
    this(anotacao.getMessage(), anotacao.getUserVinculado(),
            anotacao.getNumeroCnjVinculado(), anotacao.getDatahora());
}
}