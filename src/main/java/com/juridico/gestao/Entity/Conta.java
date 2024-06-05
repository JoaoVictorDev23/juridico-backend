package com.juridico.gestao.Entity;

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
@Table(name="conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroConta;

    private String modalidade;
    private Long numerocnj;
    private String autor;
    private String reu;
    private Date dataDeVencimento;
    private Double valorTotalAcordo;
    private Double baseCalculoHonorarios;
    private Double honorarioNegociado;
    private Integer qntDeParcela;
    private String statusAcordo;
    private Double totalRecebido;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conta")
    private List<Parcelas> parcelas;

}
