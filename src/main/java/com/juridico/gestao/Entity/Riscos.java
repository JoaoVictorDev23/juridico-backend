package com.juridico.gestao.Entity;

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
    private Long id;
    private Long numeroCnj;
    private Double riscoMinimo;
    private Double riscoRemoto;
    private Double riscoPossivel;
    private Double riscoProvavel;
    private Double riscoMaximo;
    private Double riscoEstimado;

    public Riscos(RiscosDTO riscosDTO){
        this.numeroCnj = riscosDTO.numeroCnj();
        this.riscoMinimo = riscosDTO.riscoMinimo();
        this.riscoRemoto = riscosDTO.riscoRemoto();
        this.riscoPossivel = riscosDTO.riscoPossivel();
        this.riscoProvavel = riscosDTO.riscoProvavel();
        this.riscoMaximo = riscosDTO.riscoMaximo();
        this.riscoEstimado = riscosDTO.riscoEstimado();
    }

}
