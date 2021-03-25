package com.ig.minhasfinancas.services;


import com.ig.minhasfinancas.entities.Usuario;
import com.ig.minhasfinancas.exceptions.RegraNegocioException;
import com.ig.minhasfinancas.services.UsuarioService;
import com.ig.minhasfinancas.repositories.UsuarioRepository;
import com.ig.minhasfinancas.services.impl.UsuarioServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    UsuarioService service;

    @MockBean
    UsuarioRepository repository;

    @Before
    public void setUp() {
        service = new UsuarioServiceImpl(repository);
    }

    @Test
    public void deveAutenticarUmUsuarioComSucesso() {
        // cenário
        String email = "joaquim@gmail.com";
        String senha = "senha";

        Usuario usuario = Usuario.builder().email(email).senha(senha).id(1L).build();
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

        // ação
        Usuario result = service.autenticar(email, senha);

        // verificação
        Assertions.assertThat(result).isNotNull();
    }

    @Test(expected = Test.None.class)
    public void deveValidarEmail() {
        // cenário
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);


        // ação
        service.validarEmail("joaquim@gmail.com");
    }

    @Test(expected = RegraNegocioException.class)
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
        // cenário
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

        // ação
        service.validarEmail("joaquim@gmail.com");
    }

}
