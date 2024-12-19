package com.commitoffshore.serviceshuffle.model;

import java.util.List;

public record ShuffleRequest(int number, List<Integer> shuffledList) {}
