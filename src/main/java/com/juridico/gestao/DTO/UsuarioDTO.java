package com.juridico.gestao.DTO;

import com.juridico.gestao.enums.perfis;
import com.juridico.gestao.Entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record   UsuarioDTO(
        String id,
        @NotBlank String name,
        @NotNull String email,
        @NotNull String senha,

        @NotNull String cpf,
        @NotNull perfis perfis
) {
        public UsuarioDTO(Usuario usuario) {
                this(usuario.getId(), usuario.getName(), usuario.getEmail(),usuario.getCpf(), usuario.getSenha(), usuario.getPerfis());
        }
}