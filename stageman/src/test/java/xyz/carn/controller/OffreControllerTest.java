package xyz.carn.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import xyz.carn.model.Offre;
import xyz.carn.service.OffreService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@MicronautTest
public class OffreControllerTest {
    @Inject
    OffreService service;

    @Inject
    @Client("/offres")
    HttpClient client;

    @Test
    void testGetAllOffres() {
        //Arrange
        List<Offre> expected = List.of(new Offre(), new Offre(), new Offre());
        when(service.getAllOffres()).thenReturn(expected);
        var request = HttpRequest.GET("");

        //Act
        var response = client.toBlocking().exchange(request, List.class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.OK);
        assertThat(response.body().size()).isEqualTo(expected.size());
        verify(service).getAllOffres();
    }

    @MockBean(OffreService.class)
    OffreService offreService() {
        return mock(OffreService.class);
    }
}
