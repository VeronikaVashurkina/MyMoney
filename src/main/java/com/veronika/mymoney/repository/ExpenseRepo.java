package com.veronika.mymoney.repository;

import com.veronika.mymoney.repository.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    @Query(value = "SELECT * FROM expense WHERE EXTRACT(MONTH FROM date) = :monthExpense AND EXTRACT(YEAR FROM date) = :yearExpense", nativeQuery = true)
    List<Expense> findByMonthAndYear(@Param("monthExpense") Double monthExpense, @Param("yearExpense") Double yearExpense);

    List<Expense> findByDate(Date date);
}
