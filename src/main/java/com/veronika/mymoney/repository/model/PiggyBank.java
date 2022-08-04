package com.veronika.mymoney.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "piggybank")
@Data
public class PiggyBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private BigDecimal coin;
    private Double percent;
}
