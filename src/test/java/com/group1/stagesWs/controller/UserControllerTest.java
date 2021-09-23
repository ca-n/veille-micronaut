package com.group1.stagesWs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Gestionnaire;
import com.group1.stagesWs.model.Moniteur;
import com.group1.stagesWs.model.Superviseur;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.service.UserService;
import org.junit.jupiter.api.BeforeAll;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private EtudiantRepository etudiantRepository;

    private static ObjectMapper mapper;

    @BeforeAll
    static void initializeObjectMapper() {
        mapper = new ObjectMapper().findAndRegisterModules();
    }

    @Test
    public void testAddEtudiant() throws Exception {
        //Arrange
        Etudiant expected = getEtudiant();
        when(userService.addEtudiant(expected)).thenReturn(Optional.of(expected));

        // Act
        MvcResult result = mockMvc.perform(post("/stage/etudiant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(expected))).andReturn();

        // Assert
        var actualEtudiant = mapper.readValue(result.getResponse().getContentAsString(), Etudiant.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualEtudiant).isEqualTo(expected);
    }

    @Test
    public void testAddMoniteur() throws Exception {
        //Arrange
        Moniteur expected = getMoniteur();
        when(userService.addMoniteur(expected)).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(post("/stage/moniteur")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(expected))).andReturn();

        //Assert
        var actualMoniteur = mapper.readValue(result.getResponse().getContentAsString(), Moniteur.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualMoniteur).isEqualTo(expected);
    }

    @Test
    public void testAddSuperviseur() throws Exception {
        //Arrange
        Superviseur expected = getSuperviseur();
        when(userService.addSuperviseur(expected)).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(post("/stage/superviseur")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(expected))).andReturn();

        //Assert
        var actualSuperviseur = mapper.readValue(result.getResponse().getContentAsString(), Superviseur.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualSuperviseur).isEqualTo(expected);
    }

    @Test
    void testLoginEtudiant() throws Exception {
        //Arrange
        Etudiant expected = getEtudiant();
        when(userService.addEtudiant(expected)).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(get("/user/{email}/{password}")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(expected))).andReturn();

        // Assert
        var actualEtudiant = mapper.readValue(result.getResponse().getContentAsString(), Etudiant.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualEtudiant).isEqualTo(expected);
    }

    /*
    @Test
    void testLoginGestionnaire() throws Exception {
        //Arrange
        Gestionnaire expected = getGestionnaire();
        when(userService.addGestionnaire(expected)).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(get("/user/{email}/{password}")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(expected))).andReturn();

        // Assert
        var actualGestionnaire = mapper.readValue(result.getResponse().getContentAsString(), Gestionnaire.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualGestionnaire).isEqualTo(expected);
    }
*/
    @Test
    void testLoginMoniteur() throws Exception {
        //Arrange
        Moniteur expected = getMoniteur();
        when(userService.addMoniteur(expected)).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(get("/user/{email}/{password}")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(expected))).andReturn();

        // Assert
        var actualMoniteur = mapper.readValue(result.getResponse().getContentAsString(), Moniteur.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualMoniteur).isEqualTo(expected);
    }

    @Test
    void testLoginSuperviseur() throws Exception {
        //Arrange
        Superviseur expected = getSuperviseur();
        when(userService.addSuperviseur(expected)).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(get("/user/{email}/{password}")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(expected))).andReturn();

        // Assert
        var actualSuperviseur = mapper.readValue(result.getResponse().getContentAsString(), Superviseur.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualSuperviseur).isEqualTo(expected);
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
