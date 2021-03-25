package com.ig.minhasfinancas.repositories;

import com.ig.minhasfinancas.entities.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void deveVerificarAExistenciaDeUmEmail() {
        // cenário
        Usuario usuario = Usuario.builder().nome("Joaquim").email("joaquim@gmail.com").build();
        entityManager.persist(usuario);

        // ação / execução
        boolean result = repository.existsByEmail("joaquim@gmail.com");

        // verificação
        Assertions.assertThat(result).isTrue();

    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
        // cenário

        // ação / execução
        boolean result = repository.existsByEmail("joaquim@gmail.com");

        // verificação
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void devePersistirUmUsuarioNaBaseDeDados() {
        // cenário
        Usuario usuario = criarUsuario();

        // ação
        Usuario usuarioSalvo = repository.save(usuario); // ele vai ter um id

        // verificação
        Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
    }

    @Test
    public void deveBuscarUmUsuarioPorEmail() {
        // cenário
        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);

        // verificação
        Optional<Usuario> result = repository.findByEmail("joaquim@gmail.com");
        Assertions.assertThat(result.isPresent()).isTrue();

    }

    @Test
    public void deveRetornarVazioAoBuscarUsarioPorEmailQuandoNaoExisteNaBase() {
        // verificação
        Optional<Usuario> result = repository.findByEmail("joaquim@gmail.com");
        Assertions.assertThat(result.isPresent()).isFalse();

    }

    public static Usuario criarUsuario() {
        return Usuario
                .builder()
                .nome("Joaquim")
                .email("joaquim@gmail.com")
                .senha("senha")
                .build();
    }
}
