package com.greedycalc.greedycalc;

import org.springframework.stereotype.Service;

@Service
public class CalcService {
    private final CalcRepository calcRepository;

    public CalcService(CalcRepository calcRepository) {
        this.calcRepository = calcRepository;
    }

    private CalcResponseDTO toDTO(Calculation calculation) {
        CalcResponseDTO dto = new CalcResponseDTO();
        dto.setCalcId(calculation.getCalcId());

    }

    public void saveCalculations(Calculation calculation) {
        calcRepository.save(calculation);
    }

    public Calculation getCalculation(int id) {
        return calcRepository.findById(id).get();
    }
}
