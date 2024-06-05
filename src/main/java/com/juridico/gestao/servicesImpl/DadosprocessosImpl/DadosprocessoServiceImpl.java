package com.juridico.gestao.servicesImpl.DadosprocessosImpl;

import com.juridico.gestao.DTO.*;
import com.juridico.gestao.Entity.*;
import com.juridico.gestao.repositories.*;
import com.juridico.gestao.services.Dadosprocessos.DadosprocessoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DadosprocessoServiceImpl implements DadosprocessoService {

    @Autowired
    DadosProcessosRepository dadosRepository;
    @Autowired
    ProvisaoRepository provisaoRepository;
    @Autowired
    RiscosRepository riscosRepository;
    @Autowired
    ExtrajudicialRepository extrajudicialRepository;
    @Autowired
    FinanceiroRepository financeiroRepository;

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

    @Override
    public void UpdateExtrajudicial(ExtrajudicialDTO extrajudicialDTO) {
        Extrajudicial extrajudicialexisting = extrajudicialRepository.findByNumeroCnj(extrajudicialDTO.numeroCnj());
        if(extrajudicialexisting == null){
            throw new RuntimeException("Processo não encontrado!");
        }
        extrajudicialexisting.setAnoCobranca(extrajudicialDTO.anoCobranca());
        extrajudicialexisting.setAnoQuitacao(extrajudicialDTO.anoQUitacao());
        extrajudicialexisting.setModalidade(extrajudicialDTO.modalidade());
        extrajudicialexisting.setEmpresa(extrajudicialDTO.empresa());
        extrajudicialexisting.setAdversa(extrajudicialDTO.adversa());
        extrajudicialexisting.setValorCobrado(extrajudicialDTO.valorCobrado());
        extrajudicialexisting.setSaldoRecebidoAnterior(extrajudicialDTO.saldoRecebeidoAnterior());
        extrajudicialexisting.setSaldoAreceber(extrajudicialDTO.saldoAreceber());
        extrajudicialexisting.setDescontos(extrajudicialDTO.descontos());
        extrajudicialexisting.setTotalRecebido(extrajudicialDTO.totalRecebido());
        extrajudicialexisting.setStatus(extrajudicialDTO.status());

        extrajudicialRepository.save(extrajudicialexisting);
    }

    @Override
    public void updateFinanceiro(FinanceiroDTO financeiroDTO) {
        Financeiro financeiroexisting = financeiroRepository.findByNumeroCnj(financeiroDTO.numeroCnj());
        if(financeiroexisting == null){
            throw new RuntimeException("Processo não localizado");
        }
        financeiroexisting.setContas(financeiroDTO.contas());
        financeiroRepository.save(financeiroexisting);


    }

    @Override
    public List<Dadosprocesso> getAllProcessos() {
        return dadosRepository.findAll();
    }
}
