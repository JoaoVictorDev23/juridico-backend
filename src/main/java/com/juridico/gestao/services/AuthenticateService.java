package com.juridico.gestao.services;


import com.juridico.gestao.DTO.AuthenticationDTO;

public interface AuthenticateService {
    String login(AuthenticationDTO usuario);

}
