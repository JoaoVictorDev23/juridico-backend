package com.juridico.gestao.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.juridico.gestao.DTO.RiscosDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "riscos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Riscos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="numero_cnj")
    private String numeroCnj;

    private Double riscoMinimo;

    private Double riscoRemoto;

    private Double riscoPossivel;

    private Double riscoProvavel;

    private Double riscoMaximo;

    private Double riscoEstimado;

    private Double valorCausaRisco;

    @OneToOne
    @JoinColumn(name = "dadosprocesso_id", referencedColumnName = "id")
    @JsonBackReference
    private Dadosprocesso dadosprocesso;

    public Riscos(RiscosDTO riscosDTO){
        this.numeroCnj = riscosDTO.numeroCnj();
        this.riscoMinimo = riscosDTO.riscoMinimo();
        this.riscoRemoto = riscosDTO.riscoRemoto();
        this.riscoPossivel = riscosDTO.riscoPossivel();
        this.riscoProvavel = riscosDTO.riscoProvavel();
        this.riscoMaximo = riscosDTO.riscoMaximo();
        this.riscoEstimado = riscosDTO.riscoEstimado();
        this.valorCausaRisco = riscosDTO.valorCausaRisco();
    }
    public void setDadosprocesso(Dadosprocesso dadosprocesso) {
        this.dadosprocesso = dadosprocesso;
        if (dadosprocesso != null && dadosprocesso.getRiscos() != this) {
            dadosprocesso.setRiscos(this);
        }
    }
}
