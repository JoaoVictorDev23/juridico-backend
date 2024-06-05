package com.juridico.gestao.DTO;

import com.juridico.gestao.Entity.Riscos;

public record RiscosDTO(
        Long numeroCnj,
        Double riscoMinimo,
        Double riscoRemoto,
        Double riscoPossivel,
        Double riscoProvavel,
        Double riscoMaximo,
        Double riscoEstimado
) {
    public RiscosDTO(Riscos riscos){
        this(riscos.getNumeroCnj(),riscos.getRiscoMinimo(), riscos.getRiscoRemoto(),
                riscos.getRiscoPossivel(), riscos.getRiscoProvavel(), riscos.getRiscoMaximo(),
                riscos.getRiscoEstimado());
    }
}
