package com.juridico.gestao.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.juridico.gestao.DTO.ExtrajudicialDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="extrajudicial")
public class Extrajudicial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String numeroCnj;
    private String anoCobranca;
    private String anoQuitacao;
    private String modalidade;
    private String empresa;
    private String adversa;
    private Double valorCobrado;
    private Double saldoRecebidoAnterior;
    private Double saldoAreceber;
    private Double descontos;
    private Double totalRecebido;
    private String status;
    private String emailCadastro;
    private String emailAtualizar;
    private String pasta;
    public Extrajudicial(ExtrajudicialDTO extrajudicialDTO){
        this.numeroCnj = extrajudicialDTO.numeroCnj();
        this.anoCobranca = extrajudicialDTO.anoCobranca();
        this.anoQuitacao = extrajudicialDTO.anoQuitacao();
        this.modalidade = extrajudicialDTO.modalidade();
        this.empresa = extrajudicialDTO.empresa();
        this.adversa = extrajudicialDTO.adversa();
        this.valorCobrado = extrajudicialDTO.valorCobrado();
        this.saldoRecebidoAnterior = extrajudicialDTO.saldoRecebidoAnterior();
        this.saldoAreceber = extrajudicialDTO.saldoAreceber();
        this.descontos = extrajudicialDTO.descontos();
        this.totalRecebido = extrajudicialDTO.totalRecebido();
        this.status = extrajudicialDTO.status();
        this.emailCadastro = extrajudicialDTO.emailCadastro();
        this.emailAtualizar = extrajudicialDTO.emailAtualizar();
        this.pasta = extrajudicialDTO.pasta();


    }


}
