package com.contractTest.demo.alertManagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class AlertManagementService {

    @Value("${alertManagementService.base}")
    private String alertManagementServiceBase;


    public List<String> getAlertTypes() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(alertManagementServiceBase +
                "/alert-types", String[].class);
        return Arrays.asList(responseEntity.getBody());
    }


	public List<String> getAlertTypes2() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(alertManagementServiceBase +
                "/alert-types2", String[].class);
        return Arrays.asList(responseEntity.getBody());
	}
}
