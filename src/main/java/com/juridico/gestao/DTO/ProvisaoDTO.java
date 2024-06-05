package com.juridico.gestao.DTO;

import com.juridico.gestao.Entity.Provisao;

import java.util.Date;

public record ProvisaoDTO(
        Long id,
        Long numeroCnj,
        String area,
        String adversa,
        String status,
        Date inicio,
        Date fim,
        Double saldoRescisao,
        String tempoTrabalhado,
        String fgts40,
        String avisoPrevio,
        String margem500
) {

    public ProvisaoDTO(Provisao provisao){
        this(   provisao.getId(), provisao.getNumeroCnj(), provisao.getArea(), provisao.getAdverso(),
                provisao.getStatus(), provisao.getInicio(),provisao.getFim(),provisao.getSaldoRescisao(),
                provisao.getTempoTrabalhado(), provisao.getFgts40(),
                provisao.getAvisoPrevio(),provisao.getMargem500());
    }
}
