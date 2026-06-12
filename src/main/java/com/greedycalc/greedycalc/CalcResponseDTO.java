package com.greedycalc.greedycalc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalcResponseDTO {
    private Long calcId;
    private Operation operation;
    private double value1;
    private double value2;

    private double result;
}
