package com.commitoffshore.servicelog.controller;

import com.commitoffshore.servicelog.model.ShuffleRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LogControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogRequestIntegration() throws Exception {
        mockMvc.perform(post("/api/logs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"number\": 123}"))
                .andExpect(status().isOk());
    }
}
