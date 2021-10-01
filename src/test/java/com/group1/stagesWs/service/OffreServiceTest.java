package com.group1.stagesWs.service;

import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.repositories.OffreRepository;
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
public class OffreServiceTest {

    @Mock
    private OffreRepository offreRepository;

    @InjectMocks
    private OffreService service;

    @Test
    public void testAddOffre() {
        //Arrange
        Offre expected = getOffre();
        when(offreRepository.save(any(Offre.class))).thenReturn(expected);

        //Act
        Optional<Offre> returned = service.saveOffre(expected);

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    private Offre getOffre() {
        return new Offre(
                "Developpeur Java",
                "Developpeur Java sur un projet de banque",
                "Banque NCA",
                false);
    }
}
