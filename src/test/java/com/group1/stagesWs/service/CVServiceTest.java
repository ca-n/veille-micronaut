package com.group1.stagesWs.service;

import com.group1.stagesWs.enums.CVStatus;
import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.repositories.CVRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CVServiceTest {

    @Mock
    private CVRepository cvRepository;

    @InjectMocks
    private CVService cvService;

    @Test
    void testAcceptCV() {
        //Arrange
        CV expected = new CV();
        when(cvRepository.save(any())).thenReturn(expected);

        //Act
        Optional<CV> returned = cvService.acceptCV(expected);


        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
        assertThat(returned.isPresent()).isTrue();
        assertThat(returned.get().getStatus()).isEqualTo(CVStatus.ACCEPTED);
    }

    @Test
    void testRejectCV() {
        //Arrange
        CV expected = new CV();
        when(cvRepository.save(any())).thenReturn(expected);

        //Act
        Optional<CV> returned = cvService.rejectCV(expected);


        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
        assertThat(returned.isPresent()).isTrue();
        assertThat(returned.get().getStatus()).isEqualTo(CVStatus.REJECTED);
    }

    @Test
    void testGetAllCVs() {
        //Arrange
        List<CV> expected = List.of(new CV(), new CV(), new CV());
        when(cvRepository.findAll(any(Sort.class))).thenReturn(expected);

        //Act
        List<CV> returned = cvService.getAllCVs();

        //Assert
        assertThat(returned.size()).isEqualTo(3);
    }

    @Test
    void testGetCV() {
        //Arrange
        CV expected = new CV();
        expected.setId(1);
        when(cvRepository.findById(1)).thenReturn(Optional.of(expected));

        //Act
        Optional<CV> returned = cvService.getCV(expected.getId());

        //Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }
}