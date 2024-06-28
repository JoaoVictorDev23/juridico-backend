package com.juridico.gestao.controllers;

import com.juridico.gestao.DTO.DadosprocessoDTO;
import com.juridico.gestao.DTO.ExtrajudicialDTO;
import com.juridico.gestao.Entity.Dadosprocesso;
import com.juridico.gestao.Entity.Extrajudicial;
import com.juridico.gestao.Errors.ErrorResponse;
import com.juridico.gestao.services.ExtrajudicialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extrajudicial")
public class ExtrajudicialController {

    @Autowired
    ExtrajudicialService extrajudicialService;
    @PostMapping
    public ResponseEntity createExtrajudicial(@RequestBody @Valid ExtrajudicialDTO extrajudicialDTO){

        try{
            extrajudicialService.createExtrajudicial(extrajudicialDTO);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping
    public ResponseEntity updateExtraJudicial(@RequestBody @Valid ExtrajudicialDTO extrajudicialDTO){
        try{
            extrajudicialService.updateExtrajudicial(extrajudicialDTO);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllExtrajudicial() {
        try {
            List<ExtrajudicialDTO> extrajudicial = extrajudicialService.getAllExtrajudicial();
            return ResponseEntity.ok(extrajudicial);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    @GetMapping("/{pasta}")
    public ResponseEntity<ExtrajudicialDTO> buscarporPasta(@PathVariable String pasta) {
        try {
            ExtrajudicialDTO extrajudicialDTO = extrajudicialService.buscarPorPasta(pasta);
            return ResponseEntity.ok(extrajudicialDTO);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }


}
