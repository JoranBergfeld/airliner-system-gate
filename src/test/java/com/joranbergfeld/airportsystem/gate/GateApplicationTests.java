package com.joranbergfeld.airportsystem.gate;

import com.joranbergfeld.airportsystem.gate.web.request.OccupyGateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class GateApplicationTests {

    @LocalServerPort
    int port;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldCreateAndOccupyGate() {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:" + port + "/gate";

        ResponseEntity<Gate[]> initialQueryResponse = restTemplate.getForEntity(baseUrl, Gate[].class, Collections.emptyMap());
        assertNotNull(initialQueryResponse.getBody(), "Should not respond with empty body.");
        assertEquals(0, initialQueryResponse.getBody().length, "Should not have any gates.");

        Gate gate = new Gate();
        gate.setName("Sample gate.");
        gate.setSize(123);

        ResponseEntity<Gate> createGateResponse = restTemplate.postForEntity(baseUrl, gate, Gate.class, Collections.emptyMap());
        assertEquals(201, createGateResponse.getStatusCode().value(), "Should respond with 201 status code.");
        assertNotNull(createGateResponse.getBody(), "Should not respond with empty body.");
        assertFalse(createGateResponse.getBody().isOccupied(), "Gate should not be occupied yet.");

        Gate createdGate = createGateResponse.getBody();
        OccupyGateRequest occupyGateRequest = new OccupyGateRequest();
        occupyGateRequest.setOccupyingEntityId(123L);

        ResponseEntity<Gate> occupyGateResponse = restTemplate.postForEntity(baseUrl + "/" + createdGate.getId() + "/occupy", occupyGateRequest, Gate.class, Collections.emptyMap());
        assertEquals(200, occupyGateResponse.getStatusCode().value(), "Should respond with 200 status code.");
        assertNotNull(occupyGateResponse.getBody(), "Should not respond with empty body.");
        assertTrue(occupyGateResponse.getBody().isOccupied(), "Gate should be occupied.");
        assertEquals(123L, occupyGateResponse.getBody().getEntityId(), "Gate should have the correct entity ID.");

        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {}
        });
        ResponseEntity<Gate> secondOccupyGateResponse = restTemplate.postForEntity(baseUrl + "/" + createdGate.getId() + "/occupy", occupyGateRequest, Gate.class, Collections.emptyMap());
        assertTrue(secondOccupyGateResponse.getStatusCode().is4xxClientError(), "Should throw error when trying to occupy already occupied gate.");
    }
}
