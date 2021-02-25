package com.contractTest.demo.alertManagement.service;

import com.atlassian.ta.wiremockpactgenerator.WireMockPactGenerator;
import com.contractTest.demo.alertManagement.service.AlertManagementService;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:test-application.properties")
public class AlertManagementServiceContractTest {

    private WireMockServer wireMockServer;

    @Autowired
    private AlertManagementService alertManagementService;

    /** stubbing **/
    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(8082);

        wireMockServer.addMockServiceRequestListener(
                WireMockPactGenerator
                        .builder("alert-management-consumer", "incident-service-producer")
                        .build()
        );

        wireMockServer.start();
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    /** Set expectations and verify critical business flows **/
    @Test
    public void when_incident_raised_by_consumer_expected_alertTypes_values_in_response_from_provider() {
        // given
        wireMockServer.stubFor(get(
                urlEqualTo("/alert-types"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[\"id\",\"status\",\"NewIncident\"]")));

        // when
        List<String> alertTypes = alertManagementService.getAlertTypes();

        // then
        Assertions.assertEquals("id", alertTypes.get(0));
        Assertions.assertEquals("status", alertTypes.get(1));
        Assertions.assertEquals("NewIncident", alertTypes.get(2));
    }
    
    @Test
    public void when_incident_created_by_provider_expected_alertTypes_values_in_response_from_provider() {
        // given
        wireMockServer.stubFor(get(
                urlEqualTo("/alert-types2"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[\"id\",\"status\",\"Created\"]")));

        // when
        List<String> alertTypes = alertManagementService.getAlertTypes2();

        // then
        Assertions.assertEquals("id", alertTypes.get(0));
        Assertions.assertEquals("status", alertTypes.get(1));
        Assertions.assertEquals("Created", alertTypes.get(2));
    }

}
