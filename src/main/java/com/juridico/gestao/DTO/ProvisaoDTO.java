package com.juridico.gestao.DTO;

import com.juridico.gestao.Entity.Provisao;

import java.time.LocalDate;
import java.util.Date;

public record ProvisaoDTO(
        Integer id,
        String numeroCnj,
        String area,
        String adversa,
        String status,
        LocalDate inicio,
        LocalDate fim,
        Double saldoRescisao,
        String tempoTrabalhado,
        Double fgts40,
        Double avisoPrevio,
        Double margem500,
        Double valorTProvisionado

) {

    public ProvisaoDTO(Provisao provisao){
        this(   provisao.getId(), provisao.getNumeroCnj(), provisao.getArea(), provisao.getAdverso(),
                provisao.getStatus(), provisao.getInicio(),provisao.getFim(),provisao.getSaldoRescisao(),
                provisao.getTempoTrabalhado(), provisao.getFgts40(),
                provisao.getAvisoPrevio(),provisao.getMargem500(), provisao.getValorTProvisionado());
    }
}
