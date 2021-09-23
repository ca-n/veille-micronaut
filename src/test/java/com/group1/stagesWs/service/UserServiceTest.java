package com.group1.stagesWs.service;

import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.MoniteurRepository;
import com.group1.stagesWs.repositories.SuperviseurRepository;
import com.group1.stagesWs.repositories.GestionnaireRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(expected);

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
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(expected);
        Optional<Etudiant> etudiant = service.addEtudiant(expected);

        //Act
        Optional<User> returned = service.login(expected.getCourriel(), expected.getPassword());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    /*
        @Test
        public void testLoginGestionnaire() {
            //Arrange
            Gestionnaire expected = getGestionnaire();
            when(gestionnaireRepository.save(any(Gestionnaire.class))).thenReturn(expected);
            Optional<Gestionnaire> gestionnaire = service.addGestionnaire(expected);

            //Act
            Optional<User> returned = service.login(expected.getCourriel(), expected.getPassword());

            //Assert
            assertThat(returned).isEqualTo(Optional.of(expected));
        }
    */
    @Test
    public void testLoginMoniteur() {
        //Arrange
        Moniteur expected = getMoniteur();
        when(moniteurRepository.save(any(Moniteur.class))).thenReturn(expected);
        Optional<Moniteur> moniteur = service.addMoniteur(expected);

        //Act
        Optional<User> returned = service.login(expected.getCourriel(), expected.getPassword());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testLoginSuperviseur() {
        //Arrange
        Superviseur expected = getSuperviseur();
        when(superviseurRepository.save(any(Superviseur.class))).thenReturn(expected);
        Optional<Superviseur> superviseur = service.addSuperviseur(expected);

        //Act
        Optional<User> returned = service.login(expected.getCourriel(), expected.getPassword());

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
                "1234567",
                "addy 123",
                "Technique",
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
}
