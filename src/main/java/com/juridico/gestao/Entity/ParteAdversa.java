package com.juridico.gestao.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parte_adversa")
public class ParteAdversa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String registro;
    private String telefone;
    private String endereco;
    private String email;


    @ManyToOne
    @JoinColumn(name = "dadosprocesso_id")
    @JsonBackReference

    private Dadosprocesso dadosprocesso;
}