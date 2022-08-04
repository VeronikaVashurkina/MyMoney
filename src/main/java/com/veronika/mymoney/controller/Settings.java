package com.veronika.mymoney.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Settings {

    @GetMapping("/settings")
    public String selectMonth(Model model) {
        return "settings";
    }
}
