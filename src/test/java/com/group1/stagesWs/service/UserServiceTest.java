package com.group1.stagesWs.service;

import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.GestionnaireRepository;
import com.group1.stagesWs.repositories.MoniteurRepository;
import com.group1.stagesWs.repositories.SuperviseurRepository;
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
public class UserServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    MoniteurRepository moniteurRepository;

    @Mock
    SuperviseurRepository superviseurRepository;

    @Mock
    GestionnaireRepository gestionnaireRepository;

    @InjectMocks
    private UserService service;

    @Test
    public void testAddEtudiant() {
        //Arrange
        Etudiant expected = getEtudiant();
        when(etudiantRepository.save(expected)).thenReturn(expected);

        //Act
        Optional<Etudiant> returned = service.addEtudiant(expected);

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testAddMoniteur() {
        //Arrange
        Moniteur expected = getMoniteur();
        when(moniteurRepository.save(expected)).thenReturn(expected);

        //Act
        Optional<Moniteur> returned = service.addMoniteur(expected);

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testAddSuperviseur() {
        //Arrange
        Superviseur expected = getSuperviseur();
        when(superviseurRepository.save(expected)).thenReturn(expected);

        //Act
        Optional<Superviseur> returned = service.addSuperviseur(expected);

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testLoginEtudiant() {
        //Arrange
        Etudiant expected = getEtudiant();
        when(etudiantRepository.findEtudiantByCourrielIgnoreCase(expected.getCourriel())).thenReturn(expected);
        when(etudiantRepository.findEtudiantByCourrielIgnoreCaseAndPassword(expected.getCourriel(), expected.getPassword())).thenReturn(expected);

        //Act
        Optional<User> returned = service.login(expected.getCourriel(), expected.getPassword());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testLoginGestionnaire() {
        //Arrange
        Gestionnaire expected = getGestionnaire();
        when(gestionnaireRepository.findGestionnaireByCourrielIgnoreCase(expected.getCourriel())).thenReturn(expected);
        when(gestionnaireRepository.findGestionnaireByCourrielIgnoreCaseAndPassword(expected.getCourriel(), expected.getPassword())).thenReturn(expected);

        //Act
        Optional<User> returned = service.login(expected.getCourriel(), expected.getPassword());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testLoginMoniteur() {
        //Arrange
        Moniteur expected = getMoniteur();
        when(moniteurRepository.findMoniteurByCourrielIgnoreCase(expected.getCourriel())).thenReturn(expected);
        when(moniteurRepository.findMoniteurByCourrielIgnoreCaseAndPassword(expected.getCourriel(), expected.getPassword())).thenReturn(expected);

        //Act
        Optional<User> returned = service.login(expected.getCourriel(), expected.getPassword());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testLoginSuperviseur() {
        //Arrange
        Superviseur expected = getSuperviseur();
        when(superviseurRepository.findSuperviseurByCourrielIgnoreCase(expected.getCourriel())).thenReturn(expected);
        when(superviseurRepository.findSuperviseurByCourrielIgnoreCaseAndPassword(expected.getCourriel(), expected.getPassword())).thenReturn(expected);

        //Act
        Optional<User> returned = service.login(expected.getCourriel(), expected.getPassword());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    void testLoginUserFail() {
        //Arrange

        //Act
        Optional<User> returned = service.login("", "");

        //Assert
        assertThat(returned).isEmpty();
    }

    @Test
    public void testFindEtudiantByEmail() {
        //Arrange
        Etudiant expected = getEtudiant();
        when(etudiantRepository.findEtudiantByCourrielIgnoreCase(expected.getCourriel())).thenReturn(expected);

        //Act
        Optional<User> returned = service.findUserByCourriel(expected.getCourriel());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testFindGestionnaireByEmail() {
        //Arrange
        Gestionnaire expected = getGestionnaire();
        when(gestionnaireRepository.findGestionnaireByCourrielIgnoreCase(expected.getCourriel())).thenReturn(expected);

        //Act
        Optional<User> returned = service.findUserByCourriel(expected.getCourriel());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testFindMoniteurByEmail() {
        //Arrange
        Moniteur expected = getMoniteur();
        when(moniteurRepository.findMoniteurByCourrielIgnoreCase(expected.getCourriel())).thenReturn(expected);

        //Act
        Optional<User> returned = service.findUserByCourriel(expected.getCourriel());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testFindSuperviseurByEmail() {
        //Arrange
        Superviseur expected = getSuperviseur();
        when(superviseurRepository.findSuperviseurByCourrielIgnoreCase(expected.getCourriel())).thenReturn(expected);

        //Act
        Optional<User> returned = service.findUserByCourriel(expected.getCourriel());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testFindUserByEmailNotFound() {
        //Arrange

        //Act
        Optional<User> returned = service.findUserByCourriel("");

        //Assert
        assertThat(returned).isEmpty();
    }

    @Test
    public void testGetAllEtudiants() {
        //Arrange
        List<Etudiant> expected = getEtudiants();
        when(etudiantRepository.findAll()).thenReturn(expected);

        //Act
        List<Etudiant> returned = service.getAllEtudiants();

        //Assert
        assertThat(returned).isEqualTo(expected);
    }

    @Test
    public void testFindSuperviseurByEtudiantId() {
        //Arrange
        Etudiant expected = getEtudiant();
        expected.setSuperviseur(getSuperviseur());
        when(etudiantRepository.findEtudiantById(expected.getId())).thenReturn(expected);

        //Act
        Optional<User> returned = service.findSuperviseurByEtudiantId(expected.getId());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected.getSuperviseur()));
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

    private Gestionnaire getGestionnaire() {
        return new Gestionnaire(
                "John",
                "McMurffy",
                "McMurffy@test.com",
                "password",
                "123456789",
                "Informatique");
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

    private Superviseur getSuperviseur() {
        return new Superviseur(
                "Jane",
                "Smith",
                "jane.smith@example.com",
                "pa55w0rd",
                "123000322",
                "Informatique",
                "Securite");
    }

    private List<Etudiant> getEtudiants() {
        return List.of(getEtudiant(), getEtudiant(), getEtudiant());
    }
}
