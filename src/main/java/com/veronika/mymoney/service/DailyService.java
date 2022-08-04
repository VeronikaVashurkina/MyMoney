package com.veronika.mymoney.service;

import com.veronika.mymoney.repository.ExpenseRepo;
import com.veronika.mymoney.repository.IncomeRepo;
import com.veronika.mymoney.repository.model.Expense;
import com.veronika.mymoney.repository.model.Income;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyService {
    private ExpenseRepo expenseRepo;
    private IncomeRepo incomeRepo;

    public String getDailySignNumber(Date date) {
        String resultDailySingNumber;
        if (getDailySum(date) == 0) return "0";
        if (!isNegativNumber(getDailySum(date))) {
            resultDailySingNumber = "+" + getDailySum(date).toString();
        } else {
            resultDailySingNumber = getDailySum(date).toString();
        }
        return resultDailySingNumber;
    }

    private Double getDailySum(Date date) {
        List<Expense> expenses = expenseRepo.findByDate(date);
        List<Income> incomes = incomeRepo.findByDate(date);
        BigDecimal allExpense = BigDecimal.valueOf(0);
        BigDecimal allIncomes = BigDecimal.valueOf(0);
        for (Expense e : expenses) {
            allExpense=allExpense.add(e.getCoin());
        }
        for (Income a : incomes) {
            allIncomes=allIncomes.add(a.getCoin());
        }
        return (allExpense.doubleValue() - allIncomes.doubleValue());

    }

    private boolean isNegativNumber(Double number) {
        return (number < 0);
    }
}
