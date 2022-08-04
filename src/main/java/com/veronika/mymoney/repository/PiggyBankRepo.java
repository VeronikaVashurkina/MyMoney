package com.veronika.mymoney.repository;

import com.veronika.mymoney.repository.model.Income;
import com.veronika.mymoney.repository.model.PiggyBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PiggyBankRepo extends JpaRepository<PiggyBank,Long> {
    @Query(value = "SELECT * FROM piggybank WHERE EXTRACT(MONTH FROM date) = :month AND EXTRACT(YEAR FROM date) = :year", nativeQuery = true)
    List<PiggyBank> findByMonthAndYear(@Param("month") Double month, @Param("year") Double year);
}
