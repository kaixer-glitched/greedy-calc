package com.greedycalc.greedycalc;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalcRequestDTO {

    @Enumerated(EnumType.STRING)
    private Operation operation;

    private double value1;
    private double value2;

    private double result;
}
