package com.juridico.gestao.servicesImpl;

import com.juridico.gestao.DTO.AuthenticationDTO;
import com.juridico.gestao.Entity.Usuario;
import com.juridico.gestao.Security.TokenService;
import com.juridico.gestao.services.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public String login(AuthenticationDTO usuario) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(usuario.email(), usuario.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var user = (Usuario) auth.getPrincipal();
            return generateResponse(user);
        } catch (BadCredentialsException e) {
            return "Verifique suas credenciais!";
        }
    }
    private String generateResponse(Usuario user) {
        var token = tokenService.generateToken(user);
        return token;
    }
}
