package com.juridico.gestao.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parcelas_extrajudicial")
public class ParcelasExtrajudicial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Integer numeroParcela;
    private Double valorParcela;
    private String vencimento;
    private String status;
    private Boolean pago;

    @ManyToOne
    @JoinColumn(name = "extrajudicial_id")
    @JsonBackReference
    private Extrajudicial extrajudicial;



    public void setDadosExtrajudicial(Extrajudicial extrajudicial) {
        this.extrajudicial = extrajudicial;
        if (extrajudicial != null && !extrajudicial.getParcelas().contains(this)) {
            extrajudicial.getParcelas().add(this);
        }
    }

}
