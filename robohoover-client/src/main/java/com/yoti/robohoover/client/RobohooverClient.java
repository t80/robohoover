package com.yoti.robohoover.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.springframework.http.MediaType.*;

public class RobohooverClient {
    public static final String ROBOHOOVER_CLEAN_PATH = "/robohoover/clean";

    private final ObjectMapper mapper = new ObjectMapper();
    private final URL url;
    private final RestTemplate restTemplate;

    public RobohooverClient(String host, int port) throws MalformedURLException {
        url = new URL("http://"+host+":"+port+ROBOHOOVER_CLEAN_PATH);
        restTemplate = new RestTemplate();
    }

    public ResponseEntity<RobohooverResponse> requestRoomHoovering(RobohooverRequest hooverRequest) throws IOException {
        HttpEntity<String> entity = buildEntityFor(hooverRequest);

        ResponseEntity<RobohooverResponse> response = restTemplate.exchange(
                url.toString(),
                HttpMethod.POST,
                entity,
                RobohooverResponse.class);

        return response;
    }

    private HttpEntity<String> buildEntityFor(RobohooverRequest hooverRequest) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        String jsonBody = mapper.writeValueAsString(hooverRequest);
        return new HttpEntity<>(jsonBody, headers);
    }
}
