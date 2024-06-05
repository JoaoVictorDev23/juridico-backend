package com.juridico.gestao.repositories;

import com.juridico.gestao.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Usuario findByEmail(String email);
    Usuario findByName(String name);
}
