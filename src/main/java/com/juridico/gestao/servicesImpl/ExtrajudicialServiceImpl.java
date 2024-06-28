package com.juridico.gestao.servicesImpl;

import com.juridico.gestao.DTO.DadosprocessoDTO;
import com.juridico.gestao.DTO.ExtrajudicialDTO;
import com.juridico.gestao.Entity.*;
import com.juridico.gestao.repositories.ExtrajudicialRepository;
import com.juridico.gestao.repositories.ParcelasExtrajudicialRepository;
import com.juridico.gestao.services.ExtrajudicialService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtrajudicialServiceImpl implements ExtrajudicialService {

    @Autowired
    ExtrajudicialRepository extrajudicialRepository;

    @Override
    public List<ExtrajudicialDTO> getAllExtrajudicial() {
        List<Extrajudicial> extrajudicialEntities = extrajudicialRepository.findAll();
        return extrajudicialEntities.stream()
                .map(ExtrajudicialDTO:: new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createExtrajudicial(ExtrajudicialDTO extrajudicialDTO) {

        Extrajudicial extrajudicialexisting = this.extrajudicialRepository.findByPasta(extrajudicialDTO.pasta());
        if(extrajudicialexisting != null){
            throw new RuntimeException("Extrajudicial já existe!");
        }

        Extrajudicial newExtrajudicial = new Extrajudicial(extrajudicialDTO);

        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        newExtrajudicial.setEmailCadastro(emailUsuario);
        newExtrajudicial.setEmailAtualizar(emailUsuario);


        if (newExtrajudicial.getParcelas() != null) {
            // Iterar sobre as contas do financeiro
            for (ParcelasExtrajudicial parcelasExtrajudicial: newExtrajudicial.getParcelas()) {
                parcelasExtrajudicial.setExtrajudicial(newExtrajudicial);

            }
        }


        extrajudicialRepository.save(newExtrajudicial);

    }


    // Injetar o repositório no serviço
    @Autowired
    private ParcelasExtrajudicialRepository parcelasExtrajudicialRepository;

    @Override
    @Transactional
    public void updateExtrajudicial(ExtrajudicialDTO extrajudicialDTO) {
        Extrajudicial extrajudicialExisting = extrajudicialRepository.findByPasta(extrajudicialDTO.pasta());
        if (extrajudicialExisting == null) {
            throw new RuntimeException("Processo não encontrado!");
        }

        // Atualizar campos do extrajudicial existente
        extrajudicialExisting.setAnoCobranca(extrajudicialDTO.anoCobranca());
        extrajudicialExisting.setAnoQuitacao(extrajudicialDTO.anoQuitacao());
        extrajudicialExisting.setModalidade(extrajudicialDTO.modalidade());
        extrajudicialExisting.setEmpresa(extrajudicialDTO.empresa());
        extrajudicialExisting.setAdversa(extrajudicialDTO.adversa());
        extrajudicialExisting.setValorCobrado(extrajudicialDTO.valorCobrado());
        extrajudicialExisting.setSaldoRecebidoAnterior(extrajudicialDTO.saldoRecebidoAnterior());
        extrajudicialExisting.setSaldoAreceber(extrajudicialDTO.saldoAreceber());
        extrajudicialExisting.setDescontos(extrajudicialDTO.descontos());
        extrajudicialExisting.setTotalRecebido(extrajudicialDTO.totalRecebido());
        extrajudicialExisting.setStatus(extrajudicialDTO.status());
        extrajudicialExisting.setQntParcelas(extrajudicialDTO.qntParcelas());
        extrajudicialExisting.setVencimento(extrajudicialDTO.vencimento());
        extrajudicialExisting.setObservacao(extrajudicialDTO.observacao());

        // Deletar parcelas antigas
        parcelasExtrajudicialRepository.deleteByExtrajudicial(extrajudicialExisting);

        // Adicionar novas parcelas
        extrajudicialExisting.getParcelas().clear(); // Limpa a lista de parcelas
        List<ParcelasExtrajudicial> novasParcelas = extrajudicialDTO.parcelas();
        for (ParcelasExtrajudicial novaParcela : novasParcelas) {
            novaParcela.setDadosExtrajudicial(extrajudicialExisting);
            if (novaParcela.getPago() == true) {
                novaParcela.setStatus("Pago");
            }
            extrajudicialExisting.getParcelas().add(novaParcela);
        }

        // Define o email do usuário que está atualizando
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        extrajudicialExisting.setEmailAtualizar(emailUsuario);

        // Salva a entidade atualizada
        extrajudicialRepository.save(extrajudicialExisting);
    }


    @Override
    public  ExtrajudicialDTO buscarPorPasta(String pasta){
        Extrajudicial extrajudicial = extrajudicialRepository.findByPasta(pasta);
        if (extrajudicial == null) {
            throw new RuntimeException("Extrajudicial não encontrado para a PASTA: " + pasta);
        }
        // Convertendo Dadosprocesso para DadosprocessoDTO
        ExtrajudicialDTO extrajudicialDTO = new ExtrajudicialDTO(extrajudicial);
        return extrajudicialDTO;
    }


}
