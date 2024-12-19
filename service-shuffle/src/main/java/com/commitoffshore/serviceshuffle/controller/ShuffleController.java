
package com.commitoffshore.serviceshuffle.controller;

import com.commitoffshore.serviceshuffle.dto.ShuffleRequestDto;
import com.commitoffshore.serviceshuffle.service.ShuffleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shuffle")
@Validated
public class ShuffleController {

    private final ShuffleService shuffleService;

    public ShuffleController(ShuffleService shuffleService) {
        this.shuffleService = shuffleService;
    }

    @PostMapping
    public ResponseEntity<List<Integer>> shuffleNumbers(@Valid @RequestBody  ShuffleRequestDto shuffleRequest) {
        List<Integer> result = shuffleService.getShuffledArray(shuffleRequest);
        return ResponseEntity.ok(result);
    }
}
