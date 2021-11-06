package xyz.carn.controller;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import xyz.carn.model.Etudiant;
import xyz.carn.service.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@MicronautTest
public class UserControllerTest {

    @Inject
    UserService service;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testAddEtudiant() {
        //Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setCourriel("foo.bar@example.com");
        Etudiant expected = new Etudiant();
        expected.setId(1);
        etudiant.setCourriel("foo.bar@example.com");
        when(service.addEtudiant(any(Etudiant.class))).thenReturn(Optional.of(expected));
        HttpRequest<Etudiant> request = HttpRequest.POST("/users/etudiants", etudiant);

        //Act
        HttpResponse<Etudiant> response = client.toBlocking().exchange(request, Argument.of(Etudiant.class));

        //Assert
        assertThat(response.getStatus().getCode()).isEqualTo(HttpStatus.CREATED.getCode());
        Optional<Etudiant> body = response.getBody();
        assertThat(body).isPresent();
        Etudiant actual = body.get();
        assertThat(actual.getId()).isGreaterThan(0);
    }

    @Test
    void testAddEtudiantFail() {
        //Arrange
        when(service.addEtudiant(any())).thenReturn(Optional.empty());
        HttpRequest<Etudiant> request = HttpRequest.POST("/users/etudiants", new Etudiant());

        assertThatThrownBy(() -> {
            HttpResponse<Etudiant> response = client.toBlocking().exchange(request, Argument.of(Etudiant.class));
        }).isInstanceOf(HttpClientResponseException.class).hasMessageContaining("Non-unique email.");

        //Act
        try {
            HttpResponse<Etudiant> response = client.toBlocking().exchange(request, Argument.of(Etudiant.class));
            fail("No exception");
        } catch (HttpClientResponseException ex) {
            assertThat(ex.getStatus().getCode()).isEqualTo(HttpStatus.CONFLICT.getCode());
        }
        //Assert
    }

    @MockBean(UserService.class)
    UserService userService() {
        return mock(UserService.class);
    }
}
