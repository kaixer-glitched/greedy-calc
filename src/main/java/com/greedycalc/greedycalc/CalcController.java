package com.greedycalc.greedycalc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/calc")
@RestController
public class CalcController {
    private final CalcService calcService;
    public CalcController(CalcService calcService) {
        this.calcService = calcService;
    }

    @GetMapping("/calculations")
    public ResponseEntity<CalcResponseDTO> getCalculations() {
        return calcService.findAllCalculations()
                .
    }
}
