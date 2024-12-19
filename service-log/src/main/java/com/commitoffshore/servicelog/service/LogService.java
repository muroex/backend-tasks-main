package com.commitoffshore.servicelog.service;

import com.commitoffshore.servicelog.model.ShuffleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    public void logRequest(ShuffleRequest shuffleRequest) {
        logger.info("Received shuffle request: {}", shuffleRequest);
    }
}
