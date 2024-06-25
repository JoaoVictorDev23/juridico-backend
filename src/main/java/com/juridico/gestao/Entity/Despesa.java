package com.juridico.gestao.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "despesa")
public class Despesa extends Conta {

    private Integer numeroConta;
    private String modalidade;
    private String tipoDespesa;
    private Double valor;

    private Date vencimento;
    private Date dataPagamento;
    private Double valorPago;
}