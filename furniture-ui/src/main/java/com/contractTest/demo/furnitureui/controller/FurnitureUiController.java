package com.contractTest.demo.furnitureui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.contractTest.demo.furnitureui.service.UiFurnitureService;

@Controller
public class FurnitureUiController {

    @Autowired
    private UiFurnitureService furnitureService;

    @GetMapping("/display-furniture-types")
    public String displayFurnitureTypes(Model model) {
        model.addAttribute("furnitureTypes", furnitureService.getFurnitureTypes());
        return "furnitureTypes";
    }

}
