package com.juridico.gestao.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.juridico.gestao.DTO.DadosprocessoDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

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
    private String numeroCnj;
    private String pasta;
    private String area;
    private String tipoCliente;
    private String setor;
    private String objeto;
    private String comarca;
    private String instancia;
    private String observacao;
    private String statusProcesso;
    private Double valorCausa;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataProtocolo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataBaixa;

    private Double probabilidade;
    private String classificacao;
    private String emailCadastro;
    private String emailUpdate;
    private String fase;

    @OneToOne(mappedBy = "dadosprocesso", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Riscos riscos;

    @OneToOne(mappedBy = "dadosprocesso", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Provisao provisao;

    @OneToOne(mappedBy = "dadosprocesso", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Financeiro financeiro;

    @OneToMany(mappedBy = "dadosprocesso", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference

    private List<ParteCliente> parteCliente;

    @OneToMany(mappedBy = "dadosprocesso", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference

    private List<ParteAdversa> parteAdversa;

    public Dadosprocesso(DadosprocessoDTO dadosprocessoDTO) {
        this.id = dadosprocessoDTO.id();
        this.status = dadosprocessoDTO.status();
        this.numeroCnj = dadosprocessoDTO.numeroCnj();
        this.pasta = dadosprocessoDTO.pasta();
        this.area = dadosprocessoDTO.area();
        this.tipoCliente = dadosprocessoDTO.tipoCliente();
        this.setor = dadosprocessoDTO.setor();
        this.objeto = dadosprocessoDTO.objeto();
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
        this.fase = dadosprocessoDTO.fase();
        this.riscos = dadosprocessoDTO.riscos();
        this.provisao = dadosprocessoDTO.provisao();
        this.financeiro = dadosprocessoDTO.financeiro();
        this.parteCliente = dadosprocessoDTO.parteCliente();
        this.parteAdversa = dadosprocessoDTO.parteAdversa();

        if (this.riscos != null) {
            this.riscos.setDadosprocesso(this);
        }
        if (this.provisao != null) {
            this.provisao.setDadosprocesso(this);
        }
        if (this.financeiro != null) {
            this.financeiro.setDadosprocesso(this);
        }
        if (this.parteCliente != null) {
            this.parteCliente.forEach(pc -> pc.setDadosprocesso(this));
        }
        if (this.parteAdversa != null) {
            this.parteAdversa.forEach(pa -> pa.setDadosprocesso(this));
        }
    }
}
