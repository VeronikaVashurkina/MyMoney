package com.veronika.mymoney.controller;

import com.veronika.mymoney.repository.model.Expense;
import com.veronika.mymoney.repository.model.Income;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Diagram {

    @GetMapping("/diagram")
    public String selectMonth(Model model) {
        return "diagram";
    }
}
