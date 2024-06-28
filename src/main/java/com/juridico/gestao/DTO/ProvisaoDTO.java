package com.juridico.gestao.DTO;

import com.juridico.gestao.Entity.Provisao;

import java.time.LocalDate;
import java.util.Date;

public record ProvisaoDTO(
        Integer id,
        String numeroCnj,
        LocalDate inicio,
        LocalDate fim,
        Double saldoRescisao,
        String tempoTrabalhado,
        Double fgts40,
        Double avisoPrevio,
        Double margem500,
        Double valorAcordo,
        Double totalSemAviso,
        Double totalComAviso,
        Double totalComAvisoMargem,
        Double valorDaCausa


) {

    public ProvisaoDTO(Provisao provisao){
        this(   provisao.getId(),provisao.getNumeroCnj(), provisao.getInicio(),provisao.getFim(),provisao.getSaldoRescisao(),
                provisao.getTempoTrabalhado(), provisao.getFgts40(),provisao.getAvisoPrevio(),
                provisao.getMargem500(), provisao.getValorAcordo(), provisao.getTotalSemAviso(),
                provisao.getTotalComAviso(), provisao.getTotalComAvisoMargem(), provisao.getValorDaCausa());
    }
}
