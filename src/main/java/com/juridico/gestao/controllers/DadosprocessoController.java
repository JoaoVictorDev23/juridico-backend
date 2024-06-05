package com.juridico.gestao.controllers;

import com.juridico.gestao.DTO.DadosprocessoDTO;
import com.juridico.gestao.Errors.ErrorResponse;
import com.juridico.gestao.services.Dadosprocessos.Dadosprocessos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processos")
public class DadosprocessoController {

    @Autowired
    Dadosprocessos dadosprocessos;

    @PostMapping
    public ResponseEntity createDados(@RequestBody @Valid DadosprocessoDTO dadosprocessoDTO){

        try{
            dadosprocessos.createDados(dadosprocessoDTO);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping
    public ResponseEntity<ErrorResponse> updateDados(@RequestBody @Valid DadosprocessoDTO dadosprocessoDTO) {
        try {
            dadosprocessos.updateDados(dadosprocessoDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
