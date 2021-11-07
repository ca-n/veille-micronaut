package xyz.carn.controller;

import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import xyz.carn.model.Superviseur;
import xyz.carn.service.UserService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@MicronautTest
public class UserControllerTest {
    @Inject
    UserService service;

    @Inject
    @Client("/users")
    HttpClient client;

    @Test
    void testAddSuperviseur() {
        //Arrange
        Superviseur expected = new Superviseur();
        expected.setId(1);
        when(service.addSuperviseur(any(Superviseur.class))).thenReturn(Optional.of(expected));
        var request = HttpRequest.create(HttpMethod.POST, "/superviseurs").body(new Superviseur());

        //Act
        var response = client.toBlocking().exchange(request, Superviseur.class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.body()).isEqualTo(expected);
        verify(service).addSuperviseur(any(Superviseur.class));
    }

    @Test
    void testAddEtudiant() {
        fail("Not implemented.");
    }

    @Test
    void testAddMoniteur() {
        fail("Not implemented.");
    }

    @Test
    void testLogin() {
        fail("Not implemented.");
    }

    @Test
    void testGetUserByEmail() {
        fail("Not implemented.");
    }

    @Test
    void testGetAllEtudiants() {
        fail("Not implemented.");
    }

    @MockBean(UserService.class)
    UserService service() {
        return mock(UserService.class);
    }
}
