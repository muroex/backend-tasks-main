package com.commitoffshore.serviceshuffle.service;

import com.commitoffshore.serviceshuffle.client.LogClient;
import com.commitoffshore.serviceshuffle.dto.ShuffleRequestDto;
import com.commitoffshore.serviceshuffle.model.ShuffleRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShuffleServiceTest {


    @InjectMocks
    private ShuffleService shuffleService;
    @Mock
    private LogClient logClient;


    @Test
      void testGetShuffledArray() {
        ShuffleRequestDto requestDto = new ShuffleRequestDto(5);

        List<Integer> shuffled = shuffleService.getShuffledArray(requestDto);

        assertTrue(shuffled.contains(1));
        assertTrue(shuffled.contains(2));
        assertTrue(shuffled.contains(3));
        assertTrue(shuffled.contains(4));
        assertTrue(shuffled.contains(5));

       verify(logClient, times(1)).sendLog(new ShuffleRequest(5, shuffled));
    }

    @Test
    public void testShuffle_noDuplicates() {
        ShuffleRequestDto requestDto = new ShuffleRequestDto(10);

        List<Integer> result = shuffleService.getShuffledArray(requestDto);
        assertEquals(10, result.size());
        assertEquals(result.stream().distinct().count(), result.size());
    }

    @Test
    public void testShuffle_edgeCase() {
        ShuffleRequestDto shuffleRequestDto = new ShuffleRequestDto(1);
        List<Integer> result = shuffleService.getShuffledArray(shuffleRequestDto);
        assertEquals(1, result.size());
        assertTrue(result.contains(1));
    }
}
