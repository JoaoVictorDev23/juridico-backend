package com.juridico.gestao.controllers;

import com.juridico.gestao.DTO.AuthenticationDTO;
import com.juridico.gestao.Errors.ErrorResponse;
import com.juridico.gestao.services.AuthenticateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {
    @Autowired
    private AuthenticateService authenticateService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO user){
        try{
            String response = authenticateService.login(user);
            return ResponseEntity.ok(response);
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }
}
