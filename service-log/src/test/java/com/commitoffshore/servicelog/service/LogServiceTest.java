package com.commitoffshore.servicelog.service;

import com.commitoffshore.servicelog.model.ShuffleRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
public class LogServiceTest {

    private final LogService logService=new LogService(); 

    @Test
    public void testLogRequest(CapturedOutput output) {
        ShuffleRequest shuffleRequest = new ShuffleRequest(5);
        logService.logRequest(shuffleRequest);

        assertThat(output).contains("Received shuffle request: ShuffleRequest[number=5]");
    }
}
