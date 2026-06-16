package com.greedycalc.greedycalc;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calcId;

    @Enumerated(EnumType.STRING)
    private Operation operation;

    private double value1;
    private double value2;

    private double result;
}
