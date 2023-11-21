package com.academiadodesenvolvedor.apirest.repositories;

import com.academiadodesenvolvedor.apirest.models.Usuario;
import com.academiadodesenvolvedor.apirest.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void shouldCreateAUser() {
        //ambiente/preparação
        Usuario usuario = new Usuario();
        usuario.setNome("Teste Testando Testilson");
        usuario.setEmail("testetestilson@email.com");
        usuario.setPassword("123456789");
        //execução
        this.repository.save(usuario);
        //teste/assertion
        Assertions.assertNotNull(usuario.getId());
    }

    @Test
    public void shouldDeleteUser() {
        //ambiente/preparação
        Usuario usuario = new Usuario();
        usuario.setNome("Teste Testando Testilson");
        usuario.setEmail("testetestando@email.com");
        usuario.setPassword("123456789");
        this.repository.save(usuario);

        //execução
        this.repository.delete(usuario);
        Usuario usuarioDeletado = this.repository.findByEmail("testetestando@email.com")
                .orElse(null);

        //assertion/verificação
        Assertions.assertNull(usuarioDeletado);
    }

    @Test
    public void shouldEditUser() {
        //ambiente/preparação
        Usuario usuario = new Usuario();
        usuario.setNome("Teste Testando Testilson");
        usuario.setEmail("testetestando2@email.com");
        usuario.setPassword("123456789");
        this.repository.save(usuario);

        //execução
        usuario.setEmail("testandotestudo@email.com");
        this.repository.save(usuario);
        Usuario userUpdated = this.repository.findByEmail("testestestandotestudo@email.com")
                .orElse(null);

        //teste/assertions
        Assertions.assertNotNull(userUpdated);
        Assertions.assertEquals("testestandotestudo@email.com", userUpdated.getEmail());
    }

}
