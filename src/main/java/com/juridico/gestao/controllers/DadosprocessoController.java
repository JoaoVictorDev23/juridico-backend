package com.juridico.gestao.controllers;

import com.juridico.gestao.DTO.*;
import com.juridico.gestao.Entity.Dadosprocesso;
import com.juridico.gestao.Errors.ErrorResponse;
import com.juridico.gestao.services.Dadosprocessos.DadosprocessoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/processos")
public class DadosprocessoController {

    @Autowired
    DadosprocessoService dadosprocessoService;


    @GetMapping
    public ResponseEntity<?> getAllProcessos() {
        try {
            List<Dadosprocesso> processos = dadosprocessoService.getAllProcessos();
            return ResponseEntity.ok(processos);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity createDados(@RequestBody @Valid DadosprocessoDTO dadosprocessoDTO){

        try{
            dadosprocessoService.createDados(dadosprocessoDTO);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/dados")
    public ResponseEntity updateDados(@RequestBody @Valid DadosprocessoDTO dadosprocessoDTO) {
        try {
            dadosprocessoService.updateDados(dadosprocessoDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/riscos")
    public ResponseEntity updateRiscos(@RequestBody @Valid RiscosDTO riscosDTO){
        try{
            dadosprocessoService.updateRiscos(riscosDTO);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/provisao")
    public ResponseEntity updateProvisao(@RequestBody @Valid ProvisaoDTO provisaoDTO){
        try{
            dadosprocessoService.UpdateProvisao(provisaoDTO);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/extrajudicial")
    public ResponseEntity updateExtraJudicial(@RequestBody @Valid ExtrajudicialDTO extrajudicialDTO){
        try{
            dadosprocessoService.UpdateExtrajudicial(extrajudicialDTO);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }

    @PutMapping("/financeiro")
    public ResponseEntity updateFinanceiro(@RequestBody @Valid FinanceiroDTO financeiroDTO){
        try{
            dadosprocessoService.updateFinanceiro(financeiroDTO);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }


}
