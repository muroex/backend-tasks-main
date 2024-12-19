package com.commitoffshore.servicelog.controller;

import com.commitoffshore.servicelog.model.ShuffleRequest;
import com.commitoffshore.servicelog.service.LogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/api/logs")
    @ResponseStatus(HttpStatus.OK)
    public void logRequest(@RequestBody @Valid ShuffleRequest shuffleRequest) {
        logService.logRequest(shuffleRequest);
    }
}
