package com.juridico.gestao.controllers;

import com.juridico.gestao.DTO.AnotacaoDTO;
import com.juridico.gestao.Errors.ErrorResponse;
import com.juridico.gestao.services.AnotacaoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anotacao")
public class AnotacaoController {
    @Autowired
    AnotacaoService anotacaoService;

    @PostMapping("/create")
    public ResponseEntity createMessage(@RequestBody AnotacaoDTO chatMessageDTO){
        try {
            anotacaoService.createAnotacao(chatMessageDTO);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }

    @GetMapping("{numeroCnjVinculada}")
    public ResponseEntity<List<AnotacaoDTO>> getMessagesByCnj(@PathVariable String numeroCnjVinculada) {
        try {
            List<AnotacaoDTO> messages = anotacaoService.findByNumeroCnj(numeroCnjVinculada);
            return ResponseEntity.ok(messages);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
