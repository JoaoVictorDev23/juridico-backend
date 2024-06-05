package com.juridico.gestao.services.Dadosprocessos;

import com.juridico.gestao.DTO.*;
import com.juridico.gestao.Entity.Dadosprocesso;
import com.juridico.gestao.Entity.Extrajudicial;
import com.juridico.gestao.Entity.Financeiro;
import com.juridico.gestao.Entity.Riscos;
import org.springframework.stereotype.Service;

@Service
public interface Dadosprocessos {

    void createDados(DadosprocessoDTO dadosprocesso);
    void updateDados(DadosprocessoDTO dadosprocessoDTO);

    void updateRiscos(RiscosDTO riscosDTO);

    void UpdateProvisao(ProvisaoDTO provisaoDTO);

    void updateFinanceiro(FinanceiroDTO financeiroDTO);

    void UpdateExtrajudicial(ExtrajudicialDTO extrajudicial);

}
