package com.commitoffshore.serviceshuffle.controller;

import com.commitoffshore.serviceshuffle.dto.ShuffleRequestDto;
import com.commitoffshore.serviceshuffle.service.ShuffleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShuffleControllerTest {

    @InjectMocks
    private ShuffleController shuffleController;

    @Mock
    private ShuffleService shuffleService;

    private ShuffleRequestDto validRequest;

    @BeforeEach
    void setUp() {
        validRequest = new ShuffleRequestDto(5);
    }

    @Test
    void shuffleNumbers_ShouldReturnShuffledArray_WhenRequestIsValid() {
        List<Integer> shuffledList = List.of(3, 1, 5, 2, 4);
        when(shuffleService.getShuffledArray(validRequest)).thenReturn(shuffledList);

        ResponseEntity<List<Integer>> response = shuffleController.shuffleNumbers(validRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(shuffledList, response.getBody());
    }

}
