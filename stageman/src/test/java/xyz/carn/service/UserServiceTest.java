package xyz.carn.service;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import xyz.carn.model.Etudiant;
import xyz.carn.repository.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@MicronautTest
public class UserServiceTest {

    @Inject
    UserRepository userRepository;

    @Inject
    EtudiantRepository etudiantRepository;

    @Inject
    MoniteurRepository moniteurRepository;

    @Inject
    SuperviseurRepository superviseurRepository;

    @Inject
    GestionnaireRepository gestionnaireRepository;

    @Inject
    UserService service;

    @Test
    void testAddEtudiant() {
        //Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setCourriel("foo.bar@example.com");
        Etudiant expected = new Etudiant();
        expected.setCourriel("foo.bar@example.com");
        expected.setId(1);
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(expected);

        //Act
        Optional<Etudiant> returned = service.addEtudiant(etudiant);

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getId()).isGreaterThan(0);
    }

    @MockBean(UserRepository.class)
    UserRepository userRepository() {
        return mock(UserRepository.class);
    }

    @MockBean(EtudiantRepository.class)
    EtudiantRepository etudiantRepository() {
        return mock(EtudiantRepository.class);
    }

    @MockBean(MoniteurRepository.class)
    MoniteurRepository moniteurRepository() {
        return mock(MoniteurRepository.class);
    }

    @MockBean(SuperviseurRepository.class)
    SuperviseurRepository superviseurRepository() {
        return mock(SuperviseurRepository.class);
    }

    @MockBean(GestionnaireRepository.class)
    GestionnaireRepository gestionnaireRepository() {
        return mock(GestionnaireRepository.class);
    }
}
