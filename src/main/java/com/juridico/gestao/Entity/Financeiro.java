package com.juridico.gestao.Entity;


import com.juridico.gestao.DTO.FinanceiroDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="financeiro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Financeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private Integer numeroCnj;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Conta> contas;


    public Financeiro(FinanceiroDTO financeiroDTO){
        this.numeroCnj = financeiroDTO.numeroCnj();
        this.contas = financeiroDTO.contas();
    }


}
