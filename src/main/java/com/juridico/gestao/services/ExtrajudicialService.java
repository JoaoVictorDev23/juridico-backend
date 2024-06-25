package com.juridico.gestao.services;

import com.juridico.gestao.DTO.ExtrajudicialDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExtrajudicialService {


    void createExtrajudicial (ExtrajudicialDTO extrajudicialDTO);

    void UpdateExtrajudicial(ExtrajudicialDTO extrajudicial);


    List<ExtrajudicialDTO> getAllExtrajudicial();

}
