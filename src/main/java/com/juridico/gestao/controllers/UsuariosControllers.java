package com.juridico.gestao.controllers;


import com.juridico.gestao.DTO.UsuarioDTO;
import com.juridico.gestao.Entity.Usuario;
import com.juridico.gestao.Errors.ErrorResponse;
import com.juridico.gestao.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuariosControllers {

    @Autowired
    private UsuarioRepository repository;
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsers() {
        List<Usuario> allUsuarios = repository.findAll();
        List<UsuarioDTO> usuariosDTO = allUsuarios
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuariosDTO);
    }
    @PostMapping
    public ResponseEntity createUsers(@RequestBody @Valid UsuarioDTO user ){
        if (this.repository.findByEmail(user.email()) != null) {
            ErrorResponse errorResponse = new ErrorResponse("Usuário já existe!");
            return ResponseEntity.badRequest().body(errorResponse);
    }
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.senha());
        Usuario newUsuario = new Usuario(user);
        newUsuario.setSenha(encryptedPassword);
        repository.save(newUsuario);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateUsers(@RequestBody @Valid UsuarioDTO user){

        Optional<Usuario> optionalUsuario = repository.findById(user.id());
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setName(user.name());
            usuario.setEmail(user.email());
            String encryptedPassword = new BCryptPasswordEncoder().encode(user.senha());

            usuario.setSenha(encryptedPassword);
            usuario.setPerfis(user.perfis());
            return ResponseEntity.ok(usuario);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUsuario(@PathVariable String id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UsuarioDTO> getUserByEmail(@PathVariable String email) {
        Usuario usuario = (Usuario) repository.findByEmail(email);

        if (usuario != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/criar-primeiro-usuario")
    public ResponseEntity criarPrimeiroUsuario(@RequestBody @Valid UsuarioDTO user) {
        if (repository.count() > 0) {
            // Já existe pelo menos um usuário, retorne um erro
            return ResponseEntity.badRequest().body("O primeiro usuário já foi criado.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.senha());
        Usuario newUsuario = new Usuario(user);
        newUsuario.setSenha(encryptedPassword);
        repository.save(newUsuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<UsuarioDTO> getUserByEmail() {
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario usuario = (Usuario) repository.findByEmail(emailUsuario);

        if (usuario != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
