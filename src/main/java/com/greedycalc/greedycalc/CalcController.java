package com.greedycalc.greedycalc;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/calc")
@RestController
public class CalcController {
    private final CalcService calcService;
    public CalcController(CalcService calcService) {
        this.calcService = calcService;
    }


    @GetMapping("/calculations")
    public ResponseEntity<List> getCalculations() {
        return ResponseEntity.ok(calcService.findAllCalculations());
    }

    @PostMapping
    public ResponseEntity<CalcResponseDTO> createCalculation(@Valid @RequestBody CalcRequestDTO calcRequestDTO) {
        CalcResponseDTO calcResponseDTO = calcService.calculate(calcRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(calcResponseDTO);
    }

    // orElse is fine for now since we don't have global exception handler yet
    @GetMapping("/{id}")
    public ResponseEntity<CalcResponseDTO> getCalculationById(@Valid @PathVariable Long id) {
        return calcService.getCalculationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // removed the optional here since we only need to confirm whether the actual thing exists
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalculationById(@PathVariable Long id) {
        boolean deleted = calcService.deleteCalculationById(id);
        return deleted ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}
