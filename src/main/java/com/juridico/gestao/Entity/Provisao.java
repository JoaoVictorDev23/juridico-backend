package com.juridico.gestao.Entity;


import com.juridico.gestao.DTO.ProvisaoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "provisao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long numeroCnj;

    private String area;
    private String adverso;
    private String status;
    private Date inicio;
    private Date fim;

    @Column(name="saldo_rescisao")
    private Double saldoRescisao;

    private String tempoTrabalhado;
    private String fgts40;

    @Column(name="aviso_previso")
    private String avisoPrevio;
    private String margem500;

    public Provisao(ProvisaoDTO provisaoDTO){
        this.numeroCnj = provisaoDTO.numeroCnj();
        this.area = provisaoDTO.area();
        this.adverso = provisaoDTO.adversa();
        this.status = provisaoDTO.status();
        this.inicio = provisaoDTO.inicio();
        this.fim = provisaoDTO.fim();
        this.saldoRescisao = provisaoDTO.saldoRescisao();
        this.tempoTrabalhado = provisaoDTO.tempoTrabalhado();
        this.fgts40 = provisaoDTO.fgts40();
        this.avisoPrevio = provisaoDTO.avisoPrevio();
        this.margem500 = provisaoDTO.avisoPrevio();

    }

}
