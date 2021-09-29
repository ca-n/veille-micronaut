package com.group1.stagesWs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.model.Whitelist;
import com.group1.stagesWs.service.StageService;
import com.group1.stagesWs.wrapper.OffreWhitelistWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(StageController.class)
public class StageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StageService service;

    private static ObjectMapper mapper;

    @BeforeAll
    static void initializeObjectMapper() {
        mapper = new ObjectMapper().findAndRegisterModules();
    }

    @Test
    void testAddWhitelistToOffre() throws Exception {
        //Arrange
        Offre expected = getOffre();
        Set<Etudiant> whitelisted = Set.of(new Etudiant());
        OffreWhitelistWrapper wrapper = new OffreWhitelistWrapper(expected, whitelisted);

        when(service.addWhitelistToOffre(any(Offre.class), any(Set.class))).thenReturn(Optional.of(expected));

        //Act
        MvcResult result = mockMvc.perform(post("/stage/offre/whitelist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(wrapper))).andReturn();

        //Assert
        var actualOffre = mapper.readValue(result.getResponse().getContentAsString(), Offre.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualOffre).isEqualTo(expected);
    }

    @Test
    void testGetAllOffres() throws Exception {
        //Arrange
        List<Offre> expected = List.of(getOffre(), getOffre(), getOffre());
        when(service.getAllOffres()).thenReturn(expected);

        //Act
        MvcResult result = mockMvc.perform(get("/stage/offres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(expected))).andReturn();

        //Assert
        var actualOffres = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualOffres.size()).isEqualTo(expected.size());
    }

    @Test
    void testGetEtudiantOffres() throws Exception {
        //Arrange
        List<Offre> expected = List.of(getOffre(), getOffre(), getOffre());
        when(service.getEtudiantOffres(any(Etudiant.class))).thenReturn(expected);

        //Act
        MvcResult result = mockMvc.perform(post("/stage/offres/etudiant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new Etudiant()))).andReturn();

        //Assert
        var actualOffres = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualOffres.size()).isEqualTo(expected.size());
    }

    private Offre getOffre() {
        return new Offre("Programmeur", "Full-Stack", "Super-Code Plus");
    }
}
