package com.greedycalc.greedycalc;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalcService {
    private final CalcRepository calcRepository;

    public CalcService(CalcRepository calcRepository) {
        this.calcRepository = calcRepository;
    }

    public CalcResponseDTO toResponseDTO(Calculation calculation) {
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
        return toResponseDTO(saved);
    }

    public CalcResponseDTO calculate(Calculation calculation) {
        double val1 = calculation.getValue1();
        double val2 = calculation.getValue2();
        double result = switch (calculation.getOperation()) {
            case ADD -> val1 + val2;
            case SUBTRACT ->  val1 - val2;
            case MULTIPLY -> val1 * val2 ;
            case DIVIDE -> {
                if (val2 == 0) throw new IllegalArgumentException("Cannot divide by zero");
                yield val1 / val2;
            }
            default -> throw new IllegalArgumentException("Invalid operation");
        };

        calculation.setResult(result);
        Calculation saved = calcRepository.save(calculation);
        return toResponseDTO(saved);
    }

    public CalcResponseDTO deleteCalculation(Calculation calculation) {
        Calculation existingBeforeDelete = calcRepository
                .findById(calculation.getCalcId())
                .orElseThrow(() -> new RuntimeException("Calculation not found"));
        calcRepository.delete(existingBeforeDelete);
        return toResponseDTO(existingBeforeDelete);
    }
}
