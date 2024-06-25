package com.juridico.gestao.servicesImpl;

import com.juridico.gestao.DTO.ExtrajudicialDTO;
import com.juridico.gestao.Entity.Extrajudicial;
import com.juridico.gestao.repositories.ExtrajudicialRepository;
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

        Extrajudicial extrajudicialexisting = this.extrajudicialRepository.findByNumeroCnj(extrajudicialDTO.numeroCnj());
        if(extrajudicialexisting != null){
            throw new RuntimeException("Extrajudicial já existe!");
        }

        Extrajudicial newExtrajudicial = new Extrajudicial(extrajudicialDTO);

        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(emailUsuario);
        newExtrajudicial.setEmailCadastro(emailUsuario);
        newExtrajudicial.setEmailAtualizar(emailUsuario);

        extrajudicialRepository.save(newExtrajudicial);

    }


    @Override
    @Transactional
    public void UpdateExtrajudicial(ExtrajudicialDTO extrajudicialDTO) {
        Extrajudicial extrajudicialexisting = extrajudicialRepository.findByNumeroCnj(extrajudicialDTO.numeroCnj());
        if(extrajudicialexisting == null){
            throw new RuntimeException("Processo não encontrado!");
        }
        extrajudicialexisting.setAnoCobranca(extrajudicialDTO.anoCobranca());
        extrajudicialexisting.setAnoQuitacao(extrajudicialDTO.anoQuitacao());
        extrajudicialexisting.setModalidade(extrajudicialDTO.modalidade());
        extrajudicialexisting.setEmpresa(extrajudicialDTO.empresa());
        extrajudicialexisting.setAdversa(extrajudicialDTO.adversa());
        extrajudicialexisting.setValorCobrado(extrajudicialDTO.valorCobrado());
        extrajudicialexisting.setSaldoRecebidoAnterior(extrajudicialDTO.saldoRecebidoAnterior());
        extrajudicialexisting.setSaldoAreceber(extrajudicialDTO.saldoAreceber());
        extrajudicialexisting.setDescontos(extrajudicialDTO.descontos());
        extrajudicialexisting.setTotalRecebido(extrajudicialDTO.totalRecebido());
        extrajudicialexisting.setStatus(extrajudicialDTO.status());

        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        extrajudicialexisting.setEmailAtualizar(emailUsuario);


        extrajudicialRepository.save(extrajudicialexisting);
    }

}
