package fr.canal.canal.domain.service;

import fr.canal.canal.domain.error.InvalidOperandException;
import fr.canal.canal.domain.model.Operand;
import fr.canal.canal.domain.service.operation.Addition;
import fr.canal.canal.domain.service.operation.Division;
import fr.canal.canal.domain.service.operation.Multiplication;
import fr.canal.canal.domain.service.operation.SubStraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class OperationServiceTest {
    CalculatorFactory calculatorFactory ;

    @BeforeEach
    void initParameter(){
        calculatorFactory = new CalculatorFactory();
    }

    @ParameterizedTest
    @CsvSource({
            "1 , 1 , 2",
            "1, 2, 3"
    })
    void addition(double leftOperand,double rightOperand,double expectedResult) {
       var actualResul = calculatorFactory.applyOpration(new Addition()).execute(new Operand(leftOperand,rightOperand));
        assertEquals(expectedResult,actualResul);
    }


    @ParameterizedTest
    @CsvSource({
            "1 , 1 , 0",
            "1, 2, -1"
    })
    void substraction(double leftOperand,double rightOperand,double expectedResult) {
        var actualResul = calculatorFactory.applyOpration(new SubStraction()).execute(new Operand(leftOperand,rightOperand));
        assertEquals(expectedResult,actualResul);
    }

    @ParameterizedTest
    @CsvSource({
            "1 , 1 , 1",
            "1, 2, 2"
    })
    void multiplication(double leftOperand,double rightOperand,double expectedResult) {
        var actualResul = calculatorFactory.applyOpration(new Multiplication()).execute(new Operand(leftOperand,rightOperand));
        assertEquals(expectedResult,actualResul);
    }

    @ParameterizedTest
    @CsvSource({
            "1 , 1 , 1",
            "1, 2, 0.5"
    })
    void division_when_valid_operand_then_right_value(double leftOperand,double rightOperand,double expectedResult) {
        var actualResul = calculatorFactory.applyOpration(new Division()).execute(new Operand(leftOperand,rightOperand));
        assertEquals(expectedResult,actualResul);
    }

    @ParameterizedTest
    @CsvSource({
            "1 , 0 ",
            "0, 0"
    })
    void division_when_rightOperand_is_zero_throw_exception(double leftOperand,double rightOperand) {
        var operand = new Operand(leftOperand,rightOperand);
        var operationService = calculatorFactory.applyOpration(new Division());
        InvalidOperandException exception = assertThrows(InvalidOperandException.class, () -> operationService.execute(operand));
        assertEquals("Invalid right operand for division : division by zero",exception.getMessage());
    }
}