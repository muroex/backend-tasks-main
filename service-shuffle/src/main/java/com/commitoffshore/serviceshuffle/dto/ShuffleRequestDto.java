package com.commitoffshore.serviceshuffle.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record ShuffleRequestDto(
    @Min(1) @Max(1000) int number
) {}
