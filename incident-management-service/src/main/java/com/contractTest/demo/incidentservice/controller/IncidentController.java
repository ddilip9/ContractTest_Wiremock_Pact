package com.contractTest.demo.incidentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.contractTest.demo.incidentservice.service.IncidentService;

import java.util.List;

@RestController
public class IncidentController {

    @Autowired
    private IncidentService alertManagementService;

    @RequestMapping("/alert-types")
    @ResponseBody
    public List<String> alertTypes() {
        return alertManagementService.getAlertTypes();
    }
}
