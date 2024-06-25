package com.juridico.gestao.DTO;

import com.juridico.gestao.Entity.Extrajudicial;

public record ExtrajudicialDTO(
        String numeroCnj,
        String anoCobranca,
        String anoQuitacao,
        String modalidade,
        String empresa,
        String adversa,
        Double valorCobrado,
        Double saldoRecebidoAnterior,
        Double saldoAreceber,
        Double descontos,
        Double totalRecebido,
        String status,

        String emailCadastro,

        String emailAtualizar,
        String pasta

) {

    public ExtrajudicialDTO(Extrajudicial extrajudicial){
        this(extrajudicial.getNumeroCnj(),       extrajudicial.getAnoCobranca(),           extrajudicial.getAnoQuitacao(),
                extrajudicial.getModalidade(),   extrajudicial.getEmpresa(),               extrajudicial.getAdversa(),
                extrajudicial.getValorCobrado(), extrajudicial.getSaldoRecebidoAnterior(), extrajudicial.getSaldoAreceber(),
                extrajudicial.getDescontos(),    extrajudicial.getTotalRecebido(),         extrajudicial.getStatus(),
                extrajudicial.getEmailCadastro(),extrajudicial.getEmailAtualizar(),        extrajudicial.getPasta()
                );
    }
}
