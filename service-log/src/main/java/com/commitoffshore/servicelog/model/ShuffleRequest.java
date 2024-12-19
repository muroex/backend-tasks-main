package com.commitoffshore.servicelog.model;
import jakarta.validation.constraints.NotNull;

public record ShuffleRequest(@NotNull Integer number) {
}
