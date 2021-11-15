package xyz.carn.service;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import xyz.carn.model.Etudiant;
import xyz.carn.model.Offre;
import xyz.carn.repository.EtudiantRepository;
import xyz.carn.repository.OffreRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@MicronautTest
public class OffreServiceTest {
    @Inject
    OffreRepository offreRepository;

    @Inject
    EtudiantRepository etudiantRepository;

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

    @Test
    void testSaveOffre() {
        //Arrange
        Offre expected = new Offre();
        expected.setId(1);
        when(offreRepository.save(any(Offre.class))).thenReturn(expected);

        //Act
        var returned = service.saveOffre(new Offre());

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getId()).isGreaterThan(0);
        verify(offreRepository).save(any(Offre.class));
    }

    @MockBean(OffreRepository.class)
    OffreRepository offreRepository() {
        return mock(OffreRepository.class);
    }
}
