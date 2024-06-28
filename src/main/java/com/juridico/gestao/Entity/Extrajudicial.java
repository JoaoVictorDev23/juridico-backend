package com.juridico.gestao.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.juridico.gestao.DTO.ExtrajudicialDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

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
    private String pasta;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vencimento;

    @Column(name="observacao", length = 1000)  // Especificando o tamanho da coluna message
    private String observacao;
    private Integer qntParcelas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "extrajudicial")
    @JsonManagedReference
    private List<ParcelasExtrajudicial> parcelas;

    public Extrajudicial(ExtrajudicialDTO extrajudicialDTO){
        this.pasta = extrajudicialDTO.pasta();
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
        this.parcelas = extrajudicialDTO.parcelas();
        this.vencimento = extrajudicialDTO.vencimento();
        this.qntParcelas = extrajudicialDTO.qntParcelas();
        this.observacao = extrajudicialDTO.observacao();


    }




}
