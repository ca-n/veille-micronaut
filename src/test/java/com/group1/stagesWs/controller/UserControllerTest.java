package com.group1.stagesWs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private EtudiantRepository etudiantRepository;

    private Etudiant etudiant;

    @Test
    public void saveEtudiantTest() throws Exception{
        //Arrange
        etudiant = new Etudiant();
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

        when(userService.addEtudiant(etudiant)).thenReturn(Optional.of(etudiant));

        // Act
        MvcResult result = mockMvc.perform(post("/stage/etudiant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(etudiant))).andReturn();

        // Assert
        var actualEtudiant = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Etudiant.class);
        assertThat(result.getResponse().getStatus()).isEqualTo( HttpStatus.CREATED.value());
        assertThat(etudiant).isEqualTo(actualEtudiant);
    }


}