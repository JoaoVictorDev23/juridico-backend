package com.juridico.gestao.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.juridico.gestao.DTO.FinanceiroDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "financeiro")
public class Financeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String numeroCnj;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "financeiro")
    @JsonManagedReference
    private List<Conta> contas;

    @OneToOne
    @JoinColumn(name = "dadosprocesso_id", referencedColumnName = "id")
    @JsonBackReference
    private Dadosprocesso dadosprocesso;

    public Financeiro(FinanceiroDTO financeiroDTO) {
        this.numeroCnj = financeiroDTO.numeroCnj();
        if (financeiroDTO.contas() != null) {
            this.contas = financeiroDTO.contas();
        }
    }

    public void setDadosprocesso(Dadosprocesso dadosprocesso) {
        this.dadosprocesso = dadosprocesso;
        if (dadosprocesso != null && dadosprocesso.getFinanceiro() != this) {
            dadosprocesso.setFinanceiro(this);
        }
    }
}
