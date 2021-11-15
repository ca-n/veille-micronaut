package xyz.carn.service;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import xyz.carn.repository.OffreRepository;

import static org.mockito.Mockito.mock;

@MicronautTest
public class OffreServiceTest {
    @Inject
    OffreRepository offreRepository;

    @Inject
    OffreService service;

    @MockBean(OffreRepository.class)
    OffreRepository offreRepository() {
        return mock(OffreRepository.class);
    }
}
