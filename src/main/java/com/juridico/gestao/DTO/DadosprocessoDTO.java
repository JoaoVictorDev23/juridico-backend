package com.juridico.gestao.DTO;

import com.juridico.gestao.Entity.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record DadosprocessoDTO(
        Integer id,
        String status,
        @NotNull String numeroCnj,
        String pasta,
        String area,
        String tipoCliente,
        String setor,
        String objeto,
        String comarca,
        String instancia,
        String observacao,
        String statusProcesso,
        Double valorCausa,
        LocalDate dataProtocolo,
        LocalDate dataBaixa,
        Double probabilidade,
        String classificacao,
        String emailCadastro,
        String emailUpdate,
        String fase,
        Riscos riscos,
        Provisao provisao,
        Financeiro financeiro,
        List<ParteCliente> parteCliente,
        List<ParteAdversa> parteAdversa
) {

        public DadosprocessoDTO(Dadosprocesso dadosprocesso) {
                this(dadosprocesso.getId(),
                        dadosprocesso.getStatus(),
                        dadosprocesso.getNumeroCnj(),
                        dadosprocesso.getPasta(),
                        dadosprocesso.getArea(),
                        dadosprocesso.getTipoCliente(),
                        dadosprocesso.getSetor(),
                        dadosprocesso.getObjeto(),
                        dadosprocesso.getComarca(),
                        dadosprocesso.getInstancia(),
                        dadosprocesso.getObservacao(),
                        dadosprocesso.getStatusProcesso(),
                        dadosprocesso.getValorCausa(),
                        dadosprocesso.getDataProtocolo(),
                        dadosprocesso.getDataBaixa(),
                        dadosprocesso.getProbabilidade(),
                        dadosprocesso.getClassificacao(),
                        dadosprocesso.getEmailCadastro(),
                        dadosprocesso.getEmailUpdate(),
                        dadosprocesso.getFase(),
                        dadosprocesso.getRiscos(),
                        dadosprocesso.getProvisao(),
                        dadosprocesso.getFinanceiro(),
                        dadosprocesso.getParteCliente(),
                        dadosprocesso.getParteAdversa());
        }
}
