package com.commitoffshore.serviceshuffle.client;

import com.commitoffshore.serviceshuffle.model.ShuffleRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogClientTest {

    @Mock
    private LogServiceClient logServiceClient;

    @InjectMocks
    private LogClient logClient;

    private static final Logger logger = LoggerFactory.getLogger(LogClientTest.class);

    @Test
    void sendLog_success() throws Exception {
        ShuffleRequest request = new ShuffleRequest(5, List.of(4, 2, 1, 5, 3));
        doNothing().when(logServiceClient).sendLog(request);

        CompletableFuture<Void> future = logClient.sendLog(request);

        future.get();

        verify(logServiceClient, times(1)).sendLog(request);
    }

    @Test
    void sendLog_failure() {
        ShuffleRequest request = new ShuffleRequest(5, List.of(4, 2, 1, 5, 3));
        doThrow(new RuntimeException("Internal Server Error")).when(logServiceClient).sendLog(request);

        try {
            CompletableFuture<Void> future = logClient.sendLog(request);
            future.get();
        } catch (Exception e) {
            verify(logServiceClient, times(1)).sendLog(request);
            assert e.getCause() instanceof RuntimeException;
        }
    }

    @Test
    void sendLog_logs() {
        ShuffleRequest request = new ShuffleRequest(5, List.of(4, 2, 1, 5, 3));
        doNothing().when(logServiceClient).sendLog(request);

        CompletableFuture<Void> future = logClient.sendLog(request);

        future.join();

        verify(logServiceClient, times(1)).sendLog(request);
    }
}
