package com.veronika.mymoney.controller;

import com.veronika.mymoney.repository.PiggyBankRepo;
import com.veronika.mymoney.repository.model.Expense;
import com.veronika.mymoney.repository.model.Income;
import com.veronika.mymoney.repository.model.PiggyBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PiggyBankController {


    private final PiggyBankRepo piggyBankRepo;


    @Autowired
    public PiggyBankController(PiggyBankRepo piggyBankRepo) {
        this.piggyBankRepo = piggyBankRepo;
    }

    @GetMapping("/piggyBanks")
    public String budgetPade(Model model) {
        model.addAttribute("items", piggyBankRepo.findAll());
        model.addAttribute("item", new PiggyBank());

        return "piggyBanks";
    }


    @PostMapping("/piggyBanks/add")
    public String newIncome(PiggyBank piggyBank) {
        piggyBankRepo.save(piggyBank);
        return "redirect:/piggyBanks";
    }

    @DeleteMapping("/piggyBanks/delin{id}")
    public String deleteIncome(@PathVariable("id") Long id) {
        piggyBankRepo.deleteById(id);
        return "redirect:/piggyBanks";
    }


}
