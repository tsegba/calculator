package fr.canal.canal.app.rest;

import fr.canal.canal.domain.model.Operand;
import fr.canal.canal.domain.service.operation.Addition;
import fr.canal.canal.domain.service.CalculatorFactory;
import fr.canal.canal.domain.service.operation.Division;
import fr.canal.canal.domain.service.operation.Multiplication;
import fr.canal.canal.domain.service.operation.SubStraction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/calculator")
public class CalculatorController {
    private final CalculatorFactory calculatorFactory;

    public CalculatorController(CalculatorFactory calculatorFactory) {
        this.calculatorFactory = calculatorFactory;
    }

    @PostMapping(value = "/addition")
    public ResponseEntity<Double> additionOperation(@RequestBody Operand operand) {
        final Double result = calculatorFactory.applyOpration(new Addition()).execute(operand);
        return ResponseEntity
                .ok(result);
    }
    @PostMapping(value = "/substraction")
    public ResponseEntity<Double> substractionOperation(@RequestBody Operand operand) {
        final Double result = calculatorFactory.applyOpration(new SubStraction()).execute(operand);
        return ResponseEntity
                .ok(result);
    }
    @PostMapping(value = "/multiplication")
    public ResponseEntity<Double> multiplicationOperation(@RequestBody Operand operand) {
        final Double result = calculatorFactory.applyOpration(new Multiplication()).execute(operand);
        return ResponseEntity
                .ok(result);
    }
    @PostMapping(value = "/division")
    public ResponseEntity<Double> divisionOperation(@RequestBody Operand operand) {
        final Double result = calculatorFactory.applyOpration(new Division()).execute(operand);
        return ResponseEntity
                .ok(result);
    }


}
