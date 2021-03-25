package com.ig.minhasfinancas.services;

import com.ig.minhasfinancas.entities.Usuario;
import org.springframework.stereotype.Service;


public interface UsuarioService {

    Usuario autenticar(String email, String senha);

    Usuario salvarUsuario(Usuario usuario);

    void validarEmail(String email);

}