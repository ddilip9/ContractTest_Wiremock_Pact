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

    /** We tell wiremock to generate the Pact based on stubbing we do **/
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

    /** We define a wiremock stub and then execute some expectations against it **/
    @Test
    public void alertTypes() {
        // given
        wireMockServer.stubFor(get(
                urlEqualTo("/alert-types"))
        		//urlEqualTo("/http://npis-service-virtualization-st1.npis.inttest.nbn-aws.local:8081/wfe/rest/7.0/instances"))
                .willReturn(aResponse()
                        .withStatus(200)                        
                        .withHeader("Content-Type", "application/json")
                        .withBody("[\"id\",\"status\"]")
        ));

        // when
        List<String> alertTypes = alertManagementService.getAlertTypes();

        // then
        Assertions.assertEquals("id", alertTypes.get(0));
        Assertions.assertEquals("status", alertTypes.get(1));
    }

}
