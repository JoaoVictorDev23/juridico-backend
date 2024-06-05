package com.juridico.gestao.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="parcelas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcelas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    private Integer numeroParcela;
    private Double valorParcela;
    private String vencimento;
    private String status;

}
