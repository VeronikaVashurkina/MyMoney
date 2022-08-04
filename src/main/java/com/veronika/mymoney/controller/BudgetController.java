package com.veronika.mymoney.controller;

import com.veronika.mymoney.repository.ExpenseRepo;
import com.veronika.mymoney.repository.IncomeRepo;
import com.veronika.mymoney.repository.model.Expense;
import com.veronika.mymoney.repository.model.Income;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class BudgetController {
    private final ExpenseRepo expenseRepo;
    private final IncomeRepo incomeRepo;
    private String selectDate;

    @PostMapping("/budget/addex")
    public String newExpense(Expense expense) {
        expenseRepo.save(expense);
        if (selectDate == null) {
            selectDate = "?date="+expense.getDate().toString().substring(0, 7);
        }
        return "redirect:/budget" + selectDate;
    }

    @DeleteMapping("/budget/delex{id}")
    public String deleteExpense(@PathVariable("id") Long id) {
        expenseRepo.deleteById(id);
        return "redirect:/budget" + selectDate;
    }

    @PostMapping("/budget/addin")
    public String newIncome(Income income) {
        incomeRepo.save(income);
        if (selectDate == null) {
            selectDate = "?date="+income.getDate().toString().substring(0, 7);
        }
        return "redirect:/budget" + selectDate;
    }

    @DeleteMapping("/budget/delin{id}")
    public String deleteIncome(@PathVariable("id") Long id) {
        incomeRepo.deleteById(id);
        return "redirect:/budget" + selectDate;
    }


    @GetMapping("/budget")
    public String selectMonth(Model model, String date) {
        if (date != null) {
            selectDate = "?date=" + date;
            model.addAttribute("exitems", expenseRepo.findByMonthAndYear(Double.parseDouble(date.substring(5)), Double.parseDouble(date.substring(0, 4))));
            model.addAttribute("exitem", new Expense());
            model.addAttribute("initems", incomeRepo.findByMonthAndYear(Double.parseDouble(date.substring(5)), Double.parseDouble(date.substring(0, 4))));
            model.addAttribute("initem", new Income());
            model.addAttribute("selectDate", date);
        } else {
            model.addAttribute("exitems", null);
            model.addAttribute("exitem", new Expense());
            model.addAttribute("initems", null);
            model.addAttribute("initem", new Income());
        }

        return "budget";
    }


}
