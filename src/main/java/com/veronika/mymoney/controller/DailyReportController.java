package com.veronika.mymoney.controller;

import com.veronika.mymoney.service.DailyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class DailyReportController {
    private DailyService dailyService;
    private Date date;

    @Autowired
    public DailyReportController() {
        this.date = new Date(System.currentTimeMillis());
    }

    @GetMapping("/dailyReport")
    public String selectDay(Model model) {
        model.addAttribute("exitems", dailyService.getExpenseRepo().findByDate(date));
        model.addAttribute("initems", dailyService.getIncomeRepo().findByDate(date));
        model.addAttribute("selectDate", date);
        model.addAttribute("dailySum", dailyService.getDailySignNumber(date));


        return "dailyReport";
    }

    @GetMapping("/dailyReport/next")
    public String getNextDay(){
        LocalDate localDate=getNowDay();
        localDate = localDate.plusDays(1);
        date = java.sql.Date.valueOf(localDate);
        return "redirect:/dailyReport";
    }

    @GetMapping("/dailyReport/prev")
    public String getPrevDay(){
        LocalDate localDate=getNowDay();
        localDate = localDate.minusDays(1);
        date = java.sql.Date.valueOf(localDate);
        return "redirect:/dailyReport";
    }

    private LocalDate getNowDay(){
        LocalDate localDate = date.toLocalDate();
        return localDate;
    }
}

