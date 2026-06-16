package com.greedycalc.greedycalc;

import org.springframework.stereotype.Service;

import java.util.List;
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

    public Calculation toEntity(CalcRequestDTO calcRequestDTO) {
        Calculation calculation = new Calculation();
        calculation.setOperation(calcRequestDTO.getOperation());
        calculation.setValue1(calcRequestDTO.getValue1());
        calculation.setValue2(calcRequestDTO.getValue2());
        return calculation;
    }

    public CalcResponseDTO calculate(CalcRequestDTO calcRequestDTO) {
        Calculation calculation = toEntity(calcRequestDTO);

        double val1 = calcRequestDTO.getValue1();
        double val2 = calcRequestDTO.getValue2();
        double result = switch (calcRequestDTO.getOperation()) {
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
    // what does this do??
    public List<CalcResponseDTO> findAllCalculations() {
        return calcRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
