package xyz.carn.service;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import xyz.carn.model.CV;
import xyz.carn.model.Etudiant;
import xyz.carn.model.type.CVStatus;
import xyz.carn.repository.CVRepository;
import xyz.carn.repository.EtudiantRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@MicronautTest
public class CVServiceTest {
    @Inject
    CVRepository cvRepository;

    @Inject
    EtudiantRepository etudiantRepository;

    @Inject
    CVService service;

    @Test
    void testSaveCV() {
        //Arrange
        CV expected = new CV();
        expected.setId(1);
        when(cvRepository.save(any(CV.class))).thenReturn(expected);

        //Act
        var returned = service.saveCV(new CV());

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getId()).isGreaterThan(0);
        verify(cvRepository).save(any(CV.class));
    }

    @Test
    void testGetCV() {
        //Arrange
        CV expected = new CV();
        expected.setId(1);
        when(cvRepository.findById(anyInt())).thenReturn(Optional.of(expected));

        //Act
        var returned = service.getCV(expected.getId());

        //Assert
        assertThat(returned).isPresent();
        assertThat(returned.get()).isEqualTo(expected);
        verify(cvRepository).findById(anyInt());
    }

    @Test
    void testGetAllCVs() {
        //Arrange
        List<CV> expected = List.of(new CV(), new CV(), new CV());
        when(cvRepository.findAll()).thenReturn(expected);

        //Act
        var returned = service.getAllCVs();

        //Assert
        assertThat(returned.size()).isEqualTo(3);
        verify(cvRepository).findAll();
    }

    @Test
    void testGetAllEtudiantCVs() {
        //Arrange
        List<CV> expected = List.of(new CV(), new CV(), new CV());
        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.of(new Etudiant()));
        when(cvRepository.findAllByEtudiant(any(Etudiant.class))).thenReturn(expected);

        //Act
        var returned = service.getAllEtudiantCVs(1);

        //Assert
        assertThat(returned.size()).isEqualTo(3);
        verify(etudiantRepository).findById(anyInt());
        verify(cvRepository).findAllByEtudiant(any(Etudiant.class));
    }

    @Test
    void testDeleteCV() {
        //Arrange
        when(cvRepository.findById(anyInt())).thenReturn(Optional.of(new CV()));

        //Act
        service.deleteCV(1);

        //Assert
        verify(cvRepository).findById(anyInt());
        verify(cvRepository).delete(any(CV.class));
    }

    @Test
    void testAcceptCV() {
        //Arrange
        CV cv = new CV();
        when(cvRepository.save(any(CV.class))).thenReturn(cv);

        //Act
        var returned = service.acceptCV(cv);

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getStatus()).isEqualTo(CVStatus.ACCEPTED);
        verify(cvRepository).save(any(CV.class));
    }

    @Test
    void testRejectCV() {
        //Arrange
        CV cv = new CV();
        when(cvRepository.save(any(CV.class))).thenReturn(cv);

        //Act
        var returned = service.rejectCV(cv);

        //Assert
        assertThat(returned).isPresent();
        var actual = returned.get();
        assertThat(actual.getStatus()).isEqualTo(CVStatus.REJECTED);
        verify(cvRepository).save(any(CV.class));
    }

    @Test
    void testGetPDF() {
        //Arrange
        CV expected = new CV();
        expected.setData(new byte[]{1, 2, 3, 4});
        when(cvRepository.findById(anyInt())).thenReturn(Optional.of(expected));

        //Act
        var returned = service.getPDF(1);

        //Assert
        assertThat(returned.length).isEqualTo(4);
        verify(cvRepository).findById(anyInt());
    }

    @MockBean(CVRepository.class)
    CVRepository cvRepository() {
        return mock(CVRepository.class);
    }

    @MockBean(EtudiantRepository.class)
    EtudiantRepository etudiantRepository() {
        return mock(EtudiantRepository.class);
    }
}
