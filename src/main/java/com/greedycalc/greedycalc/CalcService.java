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

    public CalcResponseDTO toDTO(Calculation calculation) {
        CalcResponseDTO calcResponseDTO = new CalcResponseDTO();
        calcResponseDTO.setCalcId(calculation.getCalcId());
        calcResponseDTO.setOperation(calculation.getOperation());
        calcResponseDTO.setValue1(calculation.getValue1());
        calcResponseDTO.setValue2(calculation.getValue2());
        calcResponseDTO.setResult(calculation.getResult());
        return calcResponseDTO;
    }

    public CalcResponseDTO saveCalculation(Calculation calculation) {
        Calculation saved = calcRepository.save(calculation);
        return toDTO(saved);
    }

}
