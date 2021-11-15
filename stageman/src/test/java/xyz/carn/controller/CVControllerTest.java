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
import xyz.carn.model.type.CVStatus;
import xyz.carn.service.CVService;

import java.util.List;
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

    @Test
    void testGetCV() {
        //Arrange
        CV expected = new CV();
        expected.setId(1);
        when(service.getCV(anyInt())).thenReturn(Optional.of(expected));
        var request = HttpRequest.GET("/" + expected.getId());

        //Act
        var response = client.toBlocking().exchange(request, CV.class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.OK);
        assertThat(response.body()).isEqualTo(expected);
        verify(service).getCV(anyInt());
    }

    @Test
    void testGetAllCVs() {
        //Arrange
        List<CV> expected = List.of(new CV(), new CV(), new CV());
        when(service.getAllCVs()).thenReturn(expected);
        var request = HttpRequest.GET("");

        //Act
        var response = client.toBlocking().exchange(request, List.class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.OK);
        assertThat(response.body().size()).isEqualTo(3);
        verify(service).getAllCVs();
    }

    @Test
    void testGetAllEtudiantCVs() {
        //Arrange
        List<CV> expected = List.of(new CV(), new CV(), new CV());
        when(service.getAllEtudiantCVs(anyInt())).thenReturn(expected);
        var request = HttpRequest.GET("/etudiant/1");

        //Act
        var response = client.toBlocking().exchange(request, List.class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.OK);
        assertThat(response.body().size()).isEqualTo(3);
        verify(service).getAllEtudiantCVs(anyInt());
    }

    @Test
    void testDeleteCV() {
        //Arrange
        var request = HttpRequest.DELETE("/1");

        //Act
        var response = client.toBlocking().exchange(request);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.OK);
        verify(service).deleteCV(anyInt());
    }

    @Test
    void testAcceptCV() {
        //Arrange
        CV expected = new CV();
        expected.setStatus(CVStatus.ACCEPTED);
        when(service.acceptCV(any(CV.class))).thenReturn(Optional.of(expected));
        var request = HttpRequest.POST("/accept", new CV());

        //Act
        var response = client.toBlocking().exchange(request, CV.class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.OK);
        assertThat(response.body().getStatus()).isEqualTo(CVStatus.ACCEPTED);
        verify(service).acceptCV(any(CV.class));
    }

    @Test
    void testRejectCV() {
        //Arrange
        CV expected = new CV();
        expected.setStatus(CVStatus.REJECTED);
        when(service.rejectCV(any(CV.class))).thenReturn(Optional.of(expected));
        var request = HttpRequest.POST("/reject", new CV());

        //Act
        var response = client.toBlocking().exchange(request, CV.class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.OK);
        assertThat(response.body().getStatus()).isEqualTo(CVStatus.REJECTED);
        verify(service).rejectCV(any(CV.class));
    }

    @Test
    void testGetPDF() {
        //Arrange
        when(service.getPDF(anyInt())).thenReturn(new byte[] {1, 2, 3, 4});
        var request = HttpRequest.GET("/1/pdf");

        //Act
        var response = client.toBlocking().exchange(request, byte[].class);

        //Assert
        assertThat(response.status()).isEqualTo(HttpStatus.OK);
        assertThat(response.header("Content-type")).isEqualTo("application/pdf");
        assertThat(response.body().length).isEqualTo(4);
        verify(service).getPDF(anyInt());
    }

    @MockBean(CVService.class)
    CVService service() {
        return mock(CVService.class);
    }
}
