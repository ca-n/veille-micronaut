package xyz.carn.service;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import xyz.carn.model.Etudiant;
import xyz.carn.model.Gestionnaire;
import xyz.carn.model.Moniteur;
import xyz.carn.model.Superviseur;
import xyz.carn.model.type.Credentials;
import xyz.carn.repository.EtudiantRepository;
import xyz.carn.repository.GestionnaireRepository;
import xyz.carn.repository.MoniteurRepository;
import xyz.carn.repository.SuperviseurRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@MicronautTest
public class UserServiceTest {
    @Inject
    GestionnaireRepository gestionnaireRepository;

    @Inject
    SuperviseurRepository superviseurRepository;

    @Inject
    EtudiantRepository etudiantRepository;

    @Inject
    MoniteurRepository moniteurRepository;

    @Inject
    UserService service;

    @Test
    void testAddSuperviseur() {
        //Arrange
        Superviseur expected = new Superviseur();
        expected.setId(1);
        when(superviseurRepository.save(any(Superviseur.class))).thenReturn(expected);

        //Act
        var returned = service.addSuperviseur(new Superviseur());

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getId()).isGreaterThan(0);
        verify(superviseurRepository).save(any(Superviseur.class));
    }

    @Test
    void testAddEtudiant() {
        //Arrange
        Etudiant expected = new Etudiant();
        expected.setId(1);
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(expected);

        //Act
        var returned = service.addEtudiant(new Etudiant());

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getId()).isGreaterThan(0);
        verify(etudiantRepository).save(any(Etudiant.class));
    }

    @Test
    void testAddMoniteur() {
        //Arrange
        Moniteur expected = new Moniteur();
        expected.setId(1);
        when(moniteurRepository.save(any(Moniteur.class))).thenReturn(expected);

        //Act
        var returned = service.addMoniteur(new Moniteur());

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getId()).isGreaterThan(0);
        verify(moniteurRepository).save(any(Moniteur.class));
    }

    @Test
    void testLoginGestionnaire() {
        //Arrange
        Credentials creds = new Credentials();
        creds.setCourriel("email@example.com");
        creds.setPassword("Password1");
        Gestionnaire expected = new Gestionnaire();
        expected.setCourriel(creds.getCourriel());
        when(gestionnaireRepository.findByCourrielIgnoreCaseAndPassword(any(String.class), any(String.class))).thenReturn(Optional.of(expected));

        //Act
        var returned = service.login(creds);

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getCourriel()).isEqualTo(expected.getCourriel());
        verify(gestionnaireRepository).findByCourrielIgnoreCaseAndPassword(any(String.class), any(String.class));
    }

    @Test
    void testLoginSuperviseur() {
        //Arrange
        Credentials creds = new Credentials();
        creds.setCourriel("email@example.com");
        creds.setPassword("Password1");
        Superviseur expected = new Superviseur();
        expected.setCourriel(creds.getCourriel());
        when(superviseurRepository.findByCourrielIgnoreCaseAndPassword(any(String.class), any(String.class))).thenReturn(Optional.of(expected));

        //Act
        var returned = service.login(creds);

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getCourriel()).isEqualTo(expected.getCourriel());
        verify(superviseurRepository).findByCourrielIgnoreCaseAndPassword(any(String.class), any(String.class));
    }

    @Test
    void testLoginEtudiant() {
        //Arrange
        Credentials creds = new Credentials();
        creds.setCourriel("email@example.com");
        creds.setPassword("Password1");
        Etudiant expected = new Etudiant();
        expected.setCourriel(creds.getCourriel());
        when(etudiantRepository.findByCourrielIgnoreCaseAndPassword(any(String.class), any(String.class))).thenReturn(Optional.of(expected));

        //Act
        var returned = service.login(creds);

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getCourriel()).isEqualTo(expected.getCourriel());
        verify(etudiantRepository).findByCourrielIgnoreCaseAndPassword(any(String.class), any(String.class));
    }

    @Test
    void testLoginMoniteur() {
        //Arrange
        Credentials creds = new Credentials();
        creds.setCourriel("email@example.com");
        creds.setPassword("Password1");
        Moniteur expected = new Moniteur();
        expected.setCourriel(creds.getCourriel());
        when(moniteurRepository.findByCourrielIgnoreCaseAndPassword(any(String.class), any(String.class))).thenReturn(Optional.of(expected));

        //Act
        var returned = service.login(creds);

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getCourriel()).isEqualTo(expected.getCourriel());
        verify(moniteurRepository).findByCourrielIgnoreCaseAndPassword(any(String.class), any(String.class));
    }

    @Test
    void testGetGestionnaireByEmail() {
        //Arrange
        Gestionnaire expected = new Gestionnaire();
        expected.setCourriel("email@example.com");
        when(gestionnaireRepository.findByCourrielIgnoreCase(any(String.class))).thenReturn(Optional.of(expected));

        //Act
        var returned = service.getUserByEmail(expected.getCourriel());

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getCourriel()).isEqualTo(expected.getCourriel());
        verify(gestionnaireRepository).findByCourrielIgnoreCase(any(String.class));
    }

    @Test
    void testGetSuperviseurByEmail() {
        //Arrange
        Superviseur expected = new Superviseur();
        expected.setCourriel("email@example.com");
        when(superviseurRepository.findByCourrielIgnoreCase(any(String.class))).thenReturn(Optional.of(expected));

        //Act
        var returned = service.getUserByEmail(expected.getCourriel());

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getCourriel()).isEqualTo(expected.getCourriel());
        verify(superviseurRepository).findByCourrielIgnoreCase(any(String.class));
    }

    @Test
    void testGetEtudiantByEmail() {
        //Arrange
        Etudiant expected = new Etudiant();
        expected.setCourriel("email@example.com");
        when(etudiantRepository.findByCourrielIgnoreCase(any(String.class))).thenReturn(Optional.of(expected));

        //Act
        var returned = service.getUserByEmail(expected.getCourriel());

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getCourriel()).isEqualTo(expected.getCourriel());
        verify(etudiantRepository).findByCourrielIgnoreCase(any(String.class));
    }

    @Test
    void testGetMoniteurByEmail() {
        //Arrange
        Moniteur expected = new Moniteur();
        expected.setCourriel("email@example.com");
        when(moniteurRepository.findByCourrielIgnoreCase(any(String.class))).thenReturn(Optional.of(expected));

        //Act
        var returned = service.getUserByEmail(expected.getCourriel());

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getCourriel()).isEqualTo(expected.getCourriel());
        verify(moniteurRepository).findByCourrielIgnoreCase(any(String.class));
    }

    @Test
    void testGetAllEtudiants() {
        //Arrange
        List<Etudiant> expected = List.of(new Etudiant(), new Etudiant(), new Etudiant());
        when(etudiantRepository.findAll()).thenReturn(expected);

        //Act
        var returned = service.getAllEtudiants();

        //Assert
        assertThat(returned.size()).isEqualTo(expected.size());
        verify(etudiantRepository).findAll();
    }

    @MockBean(GestionnaireRepository.class)
    GestionnaireRepository gestionnaireRepository() {
        return mock(GestionnaireRepository.class);
    }

    @MockBean(SuperviseurRepository.class)
    SuperviseurRepository superviseurRepository() {
        return mock(SuperviseurRepository.class);
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
