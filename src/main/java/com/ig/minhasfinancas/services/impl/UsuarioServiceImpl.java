package com.ig.minhasfinancas.services.impl;

import com.ig.minhasfinancas.entities.Usuario;
import com.ig.minhasfinancas.exceptions.ErroAutenticao;
import com.ig.minhasfinancas.exceptions.RegraNegocioException;
import com.ig.minhasfinancas.repositories.UsuarioRepository;
import com.ig.minhasfinancas.services.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuario = repository.findByEmail(email);

        if (!usuario.isPresent()) {
            throw new ErroAutenticao("Usuário não encontrado.");
        }

        if (!usuario.get().getSenha().equals(senha)) {
            throw new ErroAutenticao("Senha inválida.");
        }

        return usuario.get();
    }

    @Override
    @Transactional // vai abrir uma transação na base de dados
    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email. ");
        }
    }
}
