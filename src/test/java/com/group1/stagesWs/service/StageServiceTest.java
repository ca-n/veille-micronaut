package com.group1.stagesWs.service;

import com.group1.stagesWs.enums.CVStatus;
import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.CVRepository;
import com.group1.stagesWs.repositories.OffreRepository;
import com.group1.stagesWs.repositories.WhitelistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StageServiceTest {

    @Mock
    private OffreRepository offreRepository;

    @Mock
    private EtudiantRepository etudiantRepository;
    @Mock
    private WhitelistRepository whitelistRepository;

    @Mock
    private CVRepository cvRepository;

    @InjectMocks
    private StageService service;

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
        Whitelist expectedWhitelist = getWhitelist();
        List<Offre> expected = getOffres();
        when(etudiantRepository.findEtudiantByCourrielIgnoreCase(any(String.class))).thenReturn(expectedEtudiant);
        when(whitelistRepository.findAllByWhitelistedEtudiant(any(Etudiant.class))).thenReturn(List.of(expectedWhitelist));
        when(offreRepository.findAllByisValidTrueAndVisibiliteEtudiantIsNullOrVisibiliteEtudiantIn(any(List.class))).thenReturn(expected);

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



    @Test
    void testAcceptCV() {
        //Arrange
        CV expected = new CV();
        when(cvRepository.save(any())).thenReturn(expected);

        //Act
        Optional<CV> returned = service.acceptCV(expected);


        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
        assertThat(returned.isPresent()).isTrue();
        assertThat(returned.get().getStatus()).isEqualTo(CVStatus.ACCEPTED);
    }

    @Test
    void testRejectCV() {
        //Arrange
        CV expected = new CV();
        when(cvRepository.save(any())).thenReturn(expected);

        //Act
        Optional<CV> returned = service.rejectCV(expected);


        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
        assertThat(returned.isPresent()).isTrue();
        assertThat(returned.get().getStatus()).isEqualTo(CVStatus.REJECTED);
    }

    @Test
    void testGetAllCVs() {
        //Arrange
        List<CV> expected = List.of(new CV(), new CV(), new CV());
        when(cvRepository.findAll(any(Sort.class))).thenReturn(expected);

        //Act
        List<CV> returned = service.getAllCVs();

        //Assert
        assertThat(returned.size()).isEqualTo(3);
    }

    @Test
    void testGetCV() {
        //Arrange
        CV expected = new CV();
        expected.setId(1);
        when(cvRepository.findById(1)).thenReturn(Optional.of(expected));

        //Act
        Optional<CV> returned = service.getCV(expected.getId());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    /*
    @Test
    void testGetContratByEtudiantCourriel() {
        //Arrange
        Etudiant etudiant = getEtudiant();
        Contrat expected = getContrat();
        when(etudiantRepository.findEtudiantByCourrielIgnoreCase(any(String.class))).thenReturn(etudiant);
        when(service.getContratByEtudiantCourriel(any(String.class))).thenReturn(Optional.of(expected));

        //Act
        Optional<Contrat> returned = service.getContratByEtudiantCourriel(etudiant.getCourriel());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }
    */

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

    private Whitelist getWhitelist(){
        Whitelist whitelist = new Whitelist();
        whitelist.setWhitelistedEtudiant(Set.of(new Etudiant()));
        return whitelist;
    }


    private List<Offre> getOffres() {
        return List.of(getOffre(), getOffre(), getOffre());
    }

    private Moniteur getMoniteur() {
        return new Moniteur(
                "John",
                "Doe",
                "john.doe@example.com",
                "pa55w0rd",
                "000111222",
                "Example Enterprises",
                "123 Enterprise Lane");
    }

    private Contrat getContrat() {
        return new Contrat(LocalDate.of(2021,10,27), getEtudiant(), getMoniteur());
    }}
