package com.juridico.gestao.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acordo")
public class Acordo extends Conta {

    private Integer numeroConta;
    private String modalidade;
    private String tipoAcordo;
    private Double valorAcordo;
    private Double honorarioNegociado;

    private Double baseCalculoHonorarios;
    private Double percentualHonorario;


    private Integer qntParcela;
    private Date vencimento;
    private Date dataPagamento;
    private Double valorPago;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conta")
    @JsonManagedReference
    private List<Parcelas> parcelas;
}