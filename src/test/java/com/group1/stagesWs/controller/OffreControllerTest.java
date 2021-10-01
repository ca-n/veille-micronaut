package com.group1.stagesWs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.service.OffreService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(OffreController.class)
public class OffreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OffreService offreService;


    private static ObjectMapper mapper;

    @BeforeAll
    static void initializeObjectMapper() {
        mapper = new ObjectMapper().findAndRegisterModules();
    }

    @Test
    public void testAddOffre() throws Exception {
        //Arrange
        Offre expected = getOffre();
        when(offreService.saveOffre(expected)).thenReturn(Optional.of(expected));

        // Act
        MvcResult result = mockMvc.perform(post("/stage/offre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(expected))).andReturn();

        // Assert
        var actualOffre = mapper.readValue(result.getResponse().getContentAsString(), Offre.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualOffre).isEqualTo(expected);
    }

    private Offre getOffre() {
        return new Offre(
                "Developpeur Java",
                "Developpeur Java sur un projet de banque",
                "Banque NCA",
                false);
    }
}
