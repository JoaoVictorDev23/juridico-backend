package com.juridico.gestao.DTO;

import com.juridico.gestao.Entity.Extrajudicial;

public record ExtrajudicialDTO(
        String anoCobranca,
        String anoQUitacao,
        String modalidade,
        String empresa,
        String adversa,
        Double valorCobrado,
        Double saldoRecebeidoAnterior,
        Double saldoAreceber,
        Double descontos,
        Double totalRecebido,
        String status
) {

    public ExtrajudicialDTO(Extrajudicial extrajudicial){
        this(extrajudicial.getAnoCobranca(), extrajudicial.getAnoQuitacao(), extrajudicial.getModalidade(),
                extrajudicial.getEmpresa(), extrajudicial.getAdversa(), extrajudicial.getValorCobrado(),
                extrajudicial.getSaldoRecebidoAnterior(), extrajudicial.getSaldoAreceber(), extrajudicial.getDescontos(),
                extrajudicial.getTotalRecebido(), extrajudicial.getStatus());
    }
}
