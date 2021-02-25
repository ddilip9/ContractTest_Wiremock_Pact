package com.contractTest.demo.incidentservice.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IncidentService {

    public List<String> getAlertTypes() {
        List<String> list = new ArrayList<String>();
        list.add("id");
        list.add("status");
        list.add("NewIncident");
        list.add("Created");
        return list;
    }
}
