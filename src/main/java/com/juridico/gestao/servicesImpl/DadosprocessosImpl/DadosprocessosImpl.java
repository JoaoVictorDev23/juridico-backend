package com.juridico.gestao.servicesImpl.DadosprocessosImpl;

import com.juridico.gestao.DTO.DadosprocessoDTO;
import com.juridico.gestao.DTO.ProvisaoDTO;
import com.juridico.gestao.DTO.RiscosDTO;
import com.juridico.gestao.Entity.*;
import com.juridico.gestao.repositories.DadosProcessosRepository;
import com.juridico.gestao.repositories.ProvisaoRepository;
import com.juridico.gestao.repositories.RiscosRepository;
import com.juridico.gestao.services.Dadosprocessos.Dadosprocessos;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DadosprocessosImpl implements Dadosprocessos {

    @Autowired
    DadosProcessosRepository dadosRepository;
    ProvisaoRepository provisaoRepository;
    RiscosRepository riscosRepository;

    @Transactional
    @Override
    public void createDados (DadosprocessoDTO dadosprocessoDTO){
        Dadosprocesso dadosexisting = this.dadosRepository.findByNumeroCnj(dadosprocessoDTO.numeroCnj());
        if (dadosexisting != null) {
            throw new RuntimeException("Processo já existente!");
        }

        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Dadosprocesso newDados = new Dadosprocesso(dadosprocessoDTO);
        newDados.setEmailCadastro(emailUsuario);
        newDados.setEmailUpdate(emailUsuario);

        // Ajuste para configurar a associação entre Conta e Parcelas
        if (newDados.getFinanceiro() != null && newDados.getFinanceiro().getContas() != null) {
            for (Conta conta : newDados.getFinanceiro().getContas()) {
                if (conta.getParcelas() != null) {
                    for (Parcelas parcela : conta.getParcelas()) {
                        parcela.setConta(conta);
                    }
                }
            }
        }

        dadosRepository.save(newDados);
    }

    @Transactional
    @Override
    public void updateDados(DadosprocessoDTO dadosprocessoDTO) {
        Dadosprocesso existingDados = dadosRepository.findByNumeroCnj(dadosprocessoDTO.numeroCnj());
        if (existingDados == null) {
            throw new RuntimeException("Processo inexistente!");
        }
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        existingDados.setStatus(dadosprocessoDTO.status());
        existingDados.setNumeroCnj(dadosprocessoDTO.numeroCnj());
        existingDados.setArea(dadosprocessoDTO.area());
        existingDados.setParteCliente(dadosprocessoDTO.parteCliente());
        existingDados.setTipoCliente(dadosprocessoDTO.tipoCliente());
        existingDados.setParteAdversa(dadosprocessoDTO.parteAdversa());
        existingDados.setSetor(dadosprocessoDTO.setor());
        existingDados.setObjetivo(dadosprocessoDTO.objetivo());
        existingDados.setComarca(dadosprocessoDTO.comarca());
        existingDados.setInstancia(dadosprocessoDTO.instancia());
        existingDados.setObservacao(dadosprocessoDTO.observacao());
        existingDados.setStatusProcesso(dadosprocessoDTO.statusProcesso());
        existingDados.setValorCausa(dadosprocessoDTO.valorCausa());
        existingDados.setDataProtocolo(dadosprocessoDTO.dataProtocolo());
        existingDados.setDataBaixa(dadosprocessoDTO.dataBaixa());
        existingDados.setProbabilidade(dadosprocessoDTO.probabilidade());
        existingDados.setClassificacao(dadosprocessoDTO.classificacao());
        existingDados.setEmailUpdate(emailUsuario);


        dadosRepository.save(existingDados);
    }

    @Override
    @Transactional
    public void updateRiscos(RiscosDTO riscosDTO) {
        Riscos riscosexisting = riscosRepository.findByNumeroCnj(riscosDTO.numeroCnj());
        if (riscosexisting == null) {
            throw new RuntimeException("Processo não localizado para este risco!");
        }
        riscosexisting.setRiscoMinimo(riscosDTO.riscoMinimo());
        riscosexisting.setRiscoRemoto(riscosDTO.riscoRemoto());
        riscosexisting.setRiscoPossivel(riscosDTO.riscoPossivel());
        riscosexisting.setRiscoProvavel(riscosDTO.riscoProvavel());
        riscosexisting.setRiscoMaximo(riscosDTO.riscoMaximo());
        riscosexisting.setRiscoEstimado(riscosDTO.riscoEstimado());

        riscosRepository.save(riscosexisting);
    }

    @Override
    public void UpdateProvisao(ProvisaoDTO provisaoDTO) {
        Provisao provisaoexisting = provisaoRepository.findByNumeroCnj(provisaoDTO.numeroCnj());
        if(provisaoexisting == null){
            throw new RuntimeException("Processo não localizado para a provisão");
        }
        provisaoexisting.setArea(provisaoDTO.area());
        provisaoexisting.setAdverso(provisaoDTO.adversa());
        provisaoexisting.setStatus(provisaoDTO.status());
        provisaoexisting.setInicio(provisaoDTO.inicio());
        provisaoexisting.setFim(provisaoDTO.fim());
        provisaoexisting.setSaldoRescisao(provisaoDTO.saldoRescisao());
        provisaoexisting.setFgts40(provisaoDTO.fgts40());
        provisaoexisting.setAvisoPrevio(provisaoDTO.avisoPrevio());
        provisaoexisting.setMargem500(provisaoDTO.margem500());


        provisaoRepository.save(provisaoexisting);
    }
}
