package com.juridico.gestao.Entity;

import com.juridico.gestao.DTO.AnotacaoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "anotacao")
@Entity(name = "anotacao")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Anotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="message", length = 1000)  // Especificando o tamanho da coluna message
    private String message;

    @NotNull
    @NotBlank
    private String numeroCnjVinculado;

    @Column(name="user_vinculado")
    private String userVinculado;

    @Column(name="datahora")
    private LocalDateTime datahora;



    public Anotacao(AnotacaoDTO anotacaoDTO){
        this.message = anotacaoDTO.message();
        this.numeroCnjVinculado = anotacaoDTO.numeroCnjVinculado();

    }


}
