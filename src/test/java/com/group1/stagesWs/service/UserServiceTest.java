package com.group1.stagesWs.service;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Moniteur;
import com.group1.stagesWs.model.Superviseur;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.MoniteurRepository;
import com.group1.stagesWs.repositories.SuperviseurRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
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

    @InjectMocks
    private UserService service;

    private Etudiant etudiant;


    @BeforeEach
    public void initializeStudent(){
        etudiant = new Etudiant();
        etudiant.setPrenom("Pascal");
        etudiant.setNom("Bourgoin");
        etudiant.setCourriel("test@test.com");
        etudiant.setPassword("password");
        etudiant.setNumTelephone("123456789");
        etudiant.setNumMatricule("1234567");
        etudiant.setAdresse("addy 123");
        etudiant.setProgramme("Technique");
        etudiant.setHasLicense(true);
        etudiant.setHasVoiture(true);
    }

    @Test
    public void testInsertionEtudiant(){
       //Arrange
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        //Act
        Optional<Etudiant> etudiantTest = service.addEtudiant(etudiant);

        //Assert
        assertThat(etudiantTest).isEqualTo(Optional.of(etudiant));


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
