package com.juridico.gestao.DTO;

import com.juridico.gestao.Entity.Conta;
import com.juridico.gestao.Entity.Financeiro;

import java.util.List;

public record FinanceiroDTO(
        Integer id,
        String numeroCnj,
        List<Conta> contas
) {
    public FinanceiroDTO(Financeiro financeiro){
        this(financeiro.getId(), financeiro.getNumeroCnj(), financeiro.getContas());
    }
}
