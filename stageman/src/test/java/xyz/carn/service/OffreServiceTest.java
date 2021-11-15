package xyz.carn.service;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import xyz.carn.model.Etudiant;
import xyz.carn.model.Moniteur;
import xyz.carn.model.Offre;
import xyz.carn.repository.EtudiantRepository;
import xyz.carn.repository.MoniteurRepository;
import xyz.carn.repository.OffreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.mockito.Mockito.*;

@MicronautTest
public class OffreServiceTest {
    @Inject
    OffreRepository offreRepository;

    @Inject
    EtudiantRepository etudiantRepository;

    @Inject
    MoniteurRepository moniteurRepository;

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

    @Test
    void testGetEtudiantOffres() {
        fail("Not implemented due to issue in OffreRepository");
//        //Arrange
//        List<Offre> expected = List.of(new Offre(), new Offre(), new Offre());
//        Etudiant etudiant = new Etudiant();
//        etudiant.setId(1);
//        when(etudiantRepository.findByCourrielIgnoreCase(anyString())).thenReturn(java.util.Optional.of(etudiant));
//        when(offreRepository.findAllByWhitelistContainsAndValidTrue(any(Etudiant.class))).thenReturn(expected);
//
//        //Act
//        var returned = service.getEtudiantOffres("etudiant@example.com");
//
//        //Assert
//        assertThat(returned.size()).isEqualTo(expected.size());
    }

    @Test
    void testGetMoniteurOffres() {
        //Arrange
        List<Offre> expected = List.of(new Offre(), new Offre(), new Offre());
        when(moniteurRepository.findByCourrielIgnoreCase(anyString())).thenReturn(Optional.of(new Moniteur()));
        when(offreRepository.findAllByMoniteur(any(Moniteur.class))).thenReturn(expected);

        //Act
        var returned = service.getMoniteurOffres("moniteur@example.com");

        //Assert
        assertThat(returned.size()).isEqualTo(expected.size());
        verify(moniteurRepository).findByCourrielIgnoreCase(anyString());
        verify(offreRepository).findAllByMoniteur(any(Moniteur.class));
    }

    @Test
    void testApplyForOffre() {
        //Arrange
        Offre offre = new Offre();
        when(etudiantRepository.findByCourrielIgnoreCase(anyString())).thenReturn(Optional.of(new Etudiant()));
        when(offreRepository.findById(anyInt())).thenReturn(Optional.of(offre));
        when(offreRepository.save(any(Offre.class))).thenReturn(offre);

        //Act
        var returned = service.applyForOffre(1, "etudiant@example.com");

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getApplicants().size()).isGreaterThan(0);
    }

    @MockBean(OffreRepository.class)
    OffreRepository offreRepository() {
        return mock(OffreRepository.class);
    }

    @MockBean(EtudiantRepository.class)
    EtudiantRepository etudiantRepository() {
        return mock(EtudiantRepository.class);
    }

    @MockBean(MoniteurRepository.class)
    MoniteurRepository moniteurRepository() {
        return mock(MoniteurRepository.class);
    }
}
