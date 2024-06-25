package com.juridico.gestao.services;

import com.juridico.gestao.DTO.*;
import com.juridico.gestao.Entity.Dadosprocesso;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface DadosprocessoService {

    void createDados(DadosprocessoDTO dadosprocesso);
    void updateDados(DadosprocessoDTO dadosprocessoDTO);

    void updateRiscos(RiscosDTO riscosDTO);

    void UpdateProvisao(ProvisaoDTO provisaoDTO);

    void updateFinanceiro(FinanceiroDTO financeiroDTO);


     DadosprocessoDTO buscarPorNumeroCnj(String numeroCnj);
    List<Dadosprocesso> getAllProcessos();

    public byte[] exportToExcel() throws IOException;

}
