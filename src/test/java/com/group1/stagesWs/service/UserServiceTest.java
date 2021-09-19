package com.group1.stagesWs.service;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.repositories.EtudiantRepository;
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
        Etudiant etudiantTest = service.addEtudiant(etudiant);

        //Assert
        assertThat(etudiantTest).isEqualTo(etudiant);


    }
}
