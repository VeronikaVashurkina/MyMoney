package com.veronika.mymoney.repository;


import com.veronika.mymoney.repository.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {
    @Query(value = "SELECT * FROM income WHERE EXTRACT(MONTH FROM date) = :monthIncome AND EXTRACT(YEAR FROM date) = :yearIncome", nativeQuery = true)
    List<Income> findByMonthAndYear(@Param("monthIncome") Double monthIncome, @Param("yearIncome") Double yearIncome);
    List<Income> findByDate(Date date);
}
