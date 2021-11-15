package xyz.carn.service;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import xyz.carn.model.Offre;
import xyz.carn.repository.OffreRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@MicronautTest
public class OffreServiceTest {
    @Inject
    OffreRepository offreRepository;

    @Inject
    OffreService service;

    @Test
    void testGetAllOffres() {
        //Arrange
        List<Offre> expected = List.of(new Offre(), new Offre(), new Offre());
        when(offreRepository.findAll()).thenReturn(expected);

        //Act
        var returned = service.getAllOffres();

        //Assert
        assertThat(returned.size()).isEqualTo(expected.size());
        verify(offreRepository).findAll();
    }

    @MockBean(OffreRepository.class)
    OffreRepository offreRepository() {
        return mock(OffreRepository.class);
    }
}
