package com.juridico.gestao.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.juridico.gestao.DTO.ProvisaoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "provisao")
public class Provisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String numeroCnj;
    private String area;
    private String adverso;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fim;

    @Column(name="saldo_rescisao")
    private Double saldoRescisao;
    private String tempoTrabalhado;
    private Double fgts40;

    @Column(name="aviso_previso")
    private Double avisoPrevio;
    private Double margem500;
    @Column(name="valor_t_provisionado")
    private Double valorTProvisionado;

    @OneToOne
    @JoinColumn(name = "dadosprocesso_id", referencedColumnName = "id")
    @JsonBackReference
    private Dadosprocesso dadosprocesso;

    public Provisao(ProvisaoDTO provisaoDTO) {
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
        this.valorTProvisionado = provisaoDTO.valorTProvisionado();
    }
    public void setDadosprocesso(Dadosprocesso dadosprocesso) {
        this.dadosprocesso = dadosprocesso;
        if (dadosprocesso != null && dadosprocesso.getProvisao() != this) {
            dadosprocesso.setProvisao(this);
        }
    }
}
