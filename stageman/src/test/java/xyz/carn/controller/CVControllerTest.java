package xyz.carn.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import xyz.carn.model.CV;
import xyz.carn.service.CVService;

import java.net.URI;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@MicronautTest
public class CVControllerTest {
    @Inject
    CVService service;

    @Inject
    @Client("/cvs")
    HttpClient client;

    @Test
    void testSaveCV() {
        //Arrange
        CV expected = new CV();
        expected.setId(1);
        when(service.saveCV(any(CV.class))).thenReturn(Optional.of(expected));
        var request = HttpRequest.POST("", new CV());

        //Act
        var response = client.toBlocking().exchange(request, CV.class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.OK);
        assertThat(response.body()).isEqualTo(expected);
        verify(service).saveCV(any(CV.class));
    }

    @MockBean(CVService.class)
    CVService service() {
        return mock(CVService.class);
    }
}
