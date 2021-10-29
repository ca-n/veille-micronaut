package com.group1.stagesWs.service;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.OffreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OffreServiceTest {
    @Mock
    private OffreRepository offreRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private OffreService service;

    @Test
    void testGetAllOffres() {
        //Arrange
        List<Offre> expected = getOffres();
        when(offreRepository.findAll()).thenReturn(expected);

        //Act
        List<Offre> returned = service.getAllOffres();

        //Assert
        assertThat(returned).isEqualTo(expected);
    }

    @Test
    void testGetEtudiantOffres() {
        //Arrange
        Etudiant expectedEtudiant = getEtudiant();
        List<Offre> expected = getOffres();
        when(etudiantRepository.findEtudiantByCourrielIgnoreCase(any(String.class))).thenReturn(expectedEtudiant);
        when(offreRepository.findAllByisValidTrueAndWhitelistContains(any(Etudiant.class))).thenReturn(expected);

        //Act
        List<Offre> returned = service.getEtudiantOffres(expectedEtudiant.getCourriel());

        //Assert
        assertThat(returned).isEqualTo(expected);
    }

    @Test
    void testSaveOffre() {
        //Arrange
        Offre expected = getOffre();
        when(offreRepository.save(expected)).thenReturn(expected);

        //Act
        Optional<Offre> returned = service.saveOffre(expected);

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    private Offre getOffre() {
        return new Offre(
                "Developpeur Java",
                "Developpeur Java sur un projet de banque",
                "Banque NCA",
                false,
                "1345 Boul Leger Saint-Jean",
                "2022-1-05",
                "2022-4-05",
                13,
                "9:00 a 5:00",
                40,
                22);
    }

    private List<Offre> getOffres() {
        return List.of(getOffre(), getOffre(), getOffre());
    }

    private Etudiant getEtudiant() {
        return new Etudiant(
                "Pascal",
                "Bourgoin",
                "test@test.com",
                "password",
                "123456789",
                "technique",
                "addy 123",
                "123456",
                true,
                true);
    }
}
