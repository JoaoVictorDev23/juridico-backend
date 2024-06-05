package com.juridico.gestao.DTO;

import com.juridico.gestao.Entity.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosprocessoDTO(
         Long id,

        String status,
        @NotNull Long numeroCnj,
        String area,
        String parteCliente,
        String tipoCliente,
        String parteAdversa,
        String setor,
        String objetivo,
        String comarca,
        String instancia,
        String observacao,
        String statusProcesso,
        Double valorCausa,
        Date dataProtocolo,
        Date dataBaixa,
        Double probabilidade,
        String classificacao,
        String emailCadastro,
        String emailUpdate,
        Riscos risco,
        Provisao provisao,
        Financeiro financeiro,
        Extrajudicial extrajudicial
        ) {

        public DadosprocessoDTO(Dadosprocesso dadosprocesso){
                this(dadosprocesso.getId(), dadosprocesso.getStatus(),dadosprocesso.getNumeroCnj(),dadosprocesso.getArea(),dadosprocesso.getParteCliente(),dadosprocesso.getTipoCliente()
                        ,dadosprocesso.getParteAdversa(),dadosprocesso.getSetor(),dadosprocesso.getObjetivo(),dadosprocesso.getComarca()
                        ,dadosprocesso.getInstancia(),dadosprocesso.getObservacao(),dadosprocesso.getStatusProcesso(),dadosprocesso.getValorCausa()
                        ,dadosprocesso.getDataProtocolo(),dadosprocesso.getDataBaixa(),dadosprocesso.getProbabilidade(),dadosprocesso.getClassificacao(), dadosprocesso.getEmailCadastro(), dadosprocesso.getEmailUpdate(), dadosprocesso.getRisco()
                        ,dadosprocesso.getProvisao(),dadosprocesso.getFinanceiro(),dadosprocesso.getExtrajudicial());
        }


}
