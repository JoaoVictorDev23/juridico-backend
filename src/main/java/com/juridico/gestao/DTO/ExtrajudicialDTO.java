package com.juridico.gestao.DTO;

import com.juridico.gestao.Entity.Extrajudicial;
import com.juridico.gestao.Entity.ParcelasExtrajudicial;

import java.time.LocalDate;
import java.util.List;

public record ExtrajudicialDTO(
        String pasta,
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
        String observacao,
        LocalDate vencimento,
        Integer qntParcelas,
        List<ParcelasExtrajudicial>parcelas
) {

    public ExtrajudicialDTO(Extrajudicial extrajudicial){
        this(   extrajudicial.getPasta(),        extrajudicial.getAnoCobranca(),           extrajudicial.getAnoQuitacao(),
                extrajudicial.getModalidade(),   extrajudicial.getEmpresa(),               extrajudicial.getAdversa(),
                extrajudicial.getValorCobrado(), extrajudicial.getSaldoRecebidoAnterior(), extrajudicial.getSaldoAreceber(),
                extrajudicial.getDescontos(),    extrajudicial.getTotalRecebido(),         extrajudicial.getStatus(),
                extrajudicial.getEmailCadastro(),extrajudicial.getEmailAtualizar(),        extrajudicial.getObservacao(),
                extrajudicial.getVencimento(),   extrajudicial.getQntParcelas(),  extrajudicial.getParcelas()
                );
    }
}
