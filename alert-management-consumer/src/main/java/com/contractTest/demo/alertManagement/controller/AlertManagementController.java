package com.contractTest.demo.alertManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.contractTest.demo.alertManagement.service.AlertManagementService;

@Controller
public class AlertManagementController {

    @Autowired
    private AlertManagementService alertManagementService;

    @GetMapping("/display-alert-types")
    public String displayFurnitureTypes(Model model) {
        model.addAttribute("alertTypes", alertManagementService.getAlertTypes());
        return "alertTypes";
    }

}
