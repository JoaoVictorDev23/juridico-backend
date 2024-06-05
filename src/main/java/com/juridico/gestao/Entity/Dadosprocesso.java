package com.juridico.gestao.Entity;

import com.juridico.gestao.DTO.DadosprocessoDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dadosprocessos")
public class Dadosprocesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;
    private Integer numeroCnj;
    private String area;
    private String parteCliente;
    private String tipoCliente;
    private String parteAdversa;
    private String setor;
    private String objetivo;
    private String comarca;
    private String instancia;
    private String observacao;
    private String statusProcesso;
    private Double valorCausa;
    private Date dataProtocolo;
    private Date dataBaixa;
    private Double probabilidade;
    private String classificacao;
    private String emailCadastro;
    private String emailUpdate;

    @OneToOne(cascade = CascadeType.ALL)
    private Riscos risco;

    @OneToOne(cascade = CascadeType.ALL)
    private Provisao provisao;

    @OneToOne(cascade = CascadeType.ALL)
    private Financeiro financeiro;

    @OneToOne(cascade = CascadeType.ALL)
    private Extrajudicial extrajudicial;



    public Dadosprocesso(DadosprocessoDTO dadosprocessoDTO){
        this.status = dadosprocessoDTO.status();
        this.numeroCnj = dadosprocessoDTO.numeroCnj();
        this.area = dadosprocessoDTO.area();
        this.parteCliente = dadosprocessoDTO.parteCliente();
        this.tipoCliente = dadosprocessoDTO.tipoCliente();
        this.parteAdversa = dadosprocessoDTO.parteAdversa();
        this.setor = dadosprocessoDTO.setor();
        this.objetivo = dadosprocessoDTO.objetivo();
        this.comarca = dadosprocessoDTO.comarca();
        this.instancia = dadosprocessoDTO.instancia();
        this.observacao = dadosprocessoDTO.observacao();
        this.statusProcesso = dadosprocessoDTO.statusProcesso();
        this.valorCausa = dadosprocessoDTO.valorCausa();
        this.dataProtocolo = dadosprocessoDTO.dataProtocolo();
        this.dataBaixa = dadosprocessoDTO.dataBaixa();
        this.probabilidade = dadosprocessoDTO.probabilidade();
        this.classificacao = dadosprocessoDTO.classificacao();
        this.emailCadastro = dadosprocessoDTO.emailCadastro();
        this.emailUpdate = dadosprocessoDTO.emailUpdate();
        this.risco = dadosprocessoDTO.risco();
        this.provisao = dadosprocessoDTO.provisao();
        this.financeiro = dadosprocessoDTO.financeiro();
        this.extrajudicial = dadosprocessoDTO.extrajudicial();

    }
}


