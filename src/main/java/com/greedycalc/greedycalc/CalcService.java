package com.greedycalc.greedycalc;

import org.springframework.stereotype.Service;

@Service
public class CalcService {
    private final CalcRepository calcRepository;

    public CalcService(CalcRepository calcRepository) {
        this.calcRepository = calcRepository;
    }
}
