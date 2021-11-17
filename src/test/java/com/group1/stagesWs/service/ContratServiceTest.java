package com.group1.stagesWs.service;

import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.ContratRepository;
import com.group1.stagesWs.repositories.MoniteurRepository;
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
public class ContratServiceTest {

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private MoniteurRepository moniteurRepository;

    @InjectMocks
    private ContratService contratService;

    @Test
    void testGetAllContrats() {
        //Arrange
        List<Contrat> expected = getContrats();
        expected.get(0).setSession("HIVER_2021");
        when(contratRepository.findAll()).thenReturn(expected);

        //Act
        List<Contrat> returned = contratService.getAllContrats();

        //Assert
        assertThat(returned).hasSize(expected.size() - 1);
    }

    @Test
    void testGetContratsByMoniteurEmail() {
        //Arrange
        List<Contrat> expected = getContrats();
        Moniteur moniteur = getMoniteur();
        when(contratRepository.findContratByMoniteur(moniteur)).thenReturn(expected);
        when(moniteurRepository.findMoniteurByCourrielIgnoreCase(moniteur.getCourriel())).thenReturn(moniteur);

        //Act
        List<Contrat> returned = contratService.getContratsByMoniteurEmail(moniteur.getCourriel());

        //Assert
        assertThat(returned).isEqualTo(expected);
        assertThat(returned.size()).isEqualTo(expected.size());
    }

    @Test
    void testSaveCV() {
        //Arrange
        Contrat expected = getContrat();
        when(contratRepository.save(any(Contrat.class))).thenReturn(expected);

        //Act
        Optional<Contrat> returned = contratService.saveContrat(expected);

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
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

    private Contrat getContrat() {
        return new Contrat("fournir a l entreprise tous les renseignements concernant les conditions specifiques du programme d etudes et du programme d alternance travail etudes",
                "embaucher l eleve stagiaire aux conditions precisees dans la presente entente",
                "assumer de facon responsable et securitaire, les taches qui lui sont confiees",
                getOffre(),
                getEtudiant(),
                getMoniteur()
        );
    }

    private List<Contrat> getContrats() {
        return List.of(getContrat(), getContrat(), getContrat());
    }
}
