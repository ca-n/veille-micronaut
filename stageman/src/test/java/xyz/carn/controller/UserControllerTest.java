package xyz.carn.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import xyz.carn.model.Etudiant;
import xyz.carn.model.Moniteur;
import xyz.carn.model.Superviseur;
import xyz.carn.model.User;
import xyz.carn.model.type.Credentials;
import xyz.carn.service.UserService;

import java.util.List;
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
        var request = HttpRequest.POST("/superviseurs", new Superviseur());

        //Act
        var response = client.toBlocking().exchange(request, Superviseur.class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.body()).isEqualTo(expected);
        verify(service).addSuperviseur(any(Superviseur.class));
    }

    @Test
    void testAddEtudiant() {
        //Arrange
        Etudiant expected = new Etudiant();
        expected.setId(1);
        when(service.addEtudiant(any(Etudiant.class))).thenReturn(Optional.of(expected));
        var request = HttpRequest.POST("/etudiants", new Etudiant());

        //Act
        var response = client.toBlocking().exchange(request, Etudiant.class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.body()).isEqualTo(expected);
        verify(service).addEtudiant(any(Etudiant.class));
    }

    @Test
    void testAddMoniteur() {
        //Arrange
        Moniteur expected = new Moniteur();
        expected.setId(1);
        when(service.addMoniteur(any(Moniteur.class))).thenReturn(Optional.of(expected));
        var request = HttpRequest.POST("/moniteurs", new Moniteur());

        //Act
        var response = client.toBlocking().exchange(request, Moniteur.class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.body()).isEqualTo(expected);
        verify(service).addMoniteur(any(Moniteur.class));
    }

    @Test
    void testLogin() {
        //Arrange
        Credentials creds = new Credentials();
        creds.setCourriel("email@example.com");
        User expected = new User();
        expected.setCourriel(creds.getCourriel());
        when(service.login(any(Credentials.class))).thenReturn(Optional.of(expected));
        var request = HttpRequest.POST("/login", creds);

        //Act
        var response = client.toBlocking().exchange(request, User.class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isPresent();
        var actual = response.getBody().get();
        assertThat(actual.getCourriel()).isEqualTo(expected.getCourriel());
        verify(service).login(any(Credentials.class));
    }

    @Test
    void testGetUserByEmail() {
        //Arrange
        User expected = new User();
        expected.setCourriel("email@example.com");
        when(service.getUserByEmail(any(String.class))).thenReturn(Optional.of(expected));
        var request = HttpRequest.GET(expected.getCourriel());

        //Act
        var response = client.toBlocking().exchange(request, User.class);

        //Assert
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isPresent();
        var actual = response.getBody().get();
        assertThat(actual.getCourriel()).isEqualTo(expected.getCourriel());
        verify(service).getUserByEmail(any(String.class));
    }

    @Test
    void testGetAllEtudiants() {
        //Arrange
        List<Etudiant> expected = List.of(new Etudiant(), new Etudiant(), new Etudiant());
        when(service.getAllEtudiants()).thenReturn(expected);
        var request = HttpRequest.GET("/etudiants");

        //Act
        var response = client.toBlocking().exchange(request, List.class);

        //Assert
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isPresent();
        var actual = response.getBody().get();
        assertThat(actual.size()).isEqualTo(3);
        verify(service).getAllEtudiants();
    }

    @MockBean(UserService.class)
    UserService service() {
        return mock(UserService.class);
    }
}
