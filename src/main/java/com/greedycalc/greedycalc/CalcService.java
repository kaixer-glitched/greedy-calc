package com.greedycalc.greedycalc;

import org.springframework.stereotype.Service;

@Service
public class CalcService {
    private final CalcRepository calcRepository;

    public CalcService(CalcRepository calcRepository) {
        this.calcRepository = calcRepository;
    }
    // TODO: add save calculation to database.
    // TODO: add the main logic for calculation.
    // TODO: configure the application.properties for database.
    // TODO: create a way to send a proper DTO for the client.
}
