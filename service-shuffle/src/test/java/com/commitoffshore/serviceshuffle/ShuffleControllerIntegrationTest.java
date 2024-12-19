package com.commitoffshore.serviceshuffle;

import com.commitoffshore.serviceshuffle.client.LogServiceClient;
import com.commitoffshore.serviceshuffle.model.ShuffleRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShuffleControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private LogServiceClient logServiceClient;


    @BeforeEach
    void setUp() {
        reset(logServiceClient);
    }

    @Test
    void testShuffleAndLog() {
        ShuffleRequest shuffleRequest = new ShuffleRequest(5,null);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/shuffle", shuffleRequest, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        String shuffledArray = response.getBody();
        assertThat(shuffledArray).isNotNull();
        assertThat(shuffledArray).contains("1", "2", "3", "4", "5");
        ArgumentCaptor<ShuffleRequest> argumentCaptor = ArgumentCaptor.forClass(ShuffleRequest.class);
        verify(logServiceClient, times(1)).sendLog(argumentCaptor.capture());

        ShuffleRequest capturedRequest = argumentCaptor.getValue();
        assertThat(capturedRequest.shuffledList()).isNotNull();
        assertThat(capturedRequest.shuffledList().size()).isEqualTo(5);
    }
}
