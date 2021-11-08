package xyz.carn.service;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import xyz.carn.model.Superviseur;
import xyz.carn.repository.SuperviseurRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@MicronautTest
public class UserServiceTest {
    @Inject
    SuperviseurRepository superviseurRepository;

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
    }

    @MockBean(SuperviseurRepository.class)
    SuperviseurRepository superviseurRepository() {
        return mock(SuperviseurRepository.class);
    }
}
