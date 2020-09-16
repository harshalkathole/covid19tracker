package com.covid19.covid19tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Covid19DataController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("testName", "Test");
        return "home";
    }
}
