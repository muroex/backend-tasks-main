package com.commitoffshore.serviceshuffle.service;

import com.commitoffshore.serviceshuffle.client.LogClient;
import com.commitoffshore.serviceshuffle.dto.ShuffleRequestDto;
import com.commitoffshore.serviceshuffle.model.ShuffleRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ShuffleService {

    private final LogClient logClient;

    public ShuffleService(LogClient logClient) {
        this.logClient = logClient;
    }

    public List<Integer> getShuffledArray(ShuffleRequestDto requestDto) {
        int number = requestDto.number();

        List<Integer> numbers = IntStream.rangeClosed(1, number)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numbers);

        ShuffleRequest shuffleRequest = new ShuffleRequest(requestDto.number(), numbers);
        logClient.sendLog(shuffleRequest);

        return numbers;
    }
}
