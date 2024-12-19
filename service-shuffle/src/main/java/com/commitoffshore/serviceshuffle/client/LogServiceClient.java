package com.commitoffshore.serviceshuffle.client;

import com.commitoffshore.serviceshuffle.model.ShuffleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "logServiceClient", url = "${log.service.url:http://localhost:8081}")
public interface LogServiceClient {

    @PostMapping("/api/logs")
    void sendLog(@RequestBody ShuffleRequest shuffleRequest);
}
