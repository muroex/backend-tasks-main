package com.commitoffshore.serviceshuffle.client;

import com.commitoffshore.serviceshuffle.exception.LogServiceException;
import com.commitoffshore.serviceshuffle.model.ShuffleRequest;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class LogClient {

    private static final Logger logger = LoggerFactory.getLogger(LogClient.class);

    private final LogServiceClient logServiceClient;

    public LogClient(LogServiceClient logServiceClient) {
        this.logServiceClient = logServiceClient;
    }

    @Async
    public CompletableFuture<Void> sendLog(ShuffleRequest shuffleRequest) {
        logger.info("Initiating asynchronous log send for shuffleRequest: {}", shuffleRequest);

        return CompletableFuture.runAsync(() -> {
            try {
                logServiceClient.sendLog(shuffleRequest);
                logger.info("Log successfully sent for shuffleRequest: {}", shuffleRequest);
            } catch (FeignException fe) {
                logger.error("Feign error occurred while sending log for shuffleRequest {}: {}", shuffleRequest, fe.getMessage(), fe);
                throw new LogServiceException("Error communicating with the log service", fe);  // Custom exception
            } catch (Exception e) {
                logger.error("Unexpected error occurred while sending log for shuffleRequest {}: {}", shuffleRequest, e.getMessage(), e);
                throw new LogServiceException("Unexpected error occurred", e);  // Custom exception
            }
        }).exceptionally(ex -> {
            logger.error("Failed to send log asynchronously: {}", ex.getMessage(), ex);
            return null;
        });
    }
}
