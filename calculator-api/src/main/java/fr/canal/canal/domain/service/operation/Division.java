package fr.canal.canal.domain.service.operation;

import fr.canal.canal.domain.error.InvalidOperandException;
import fr.canal.canal.domain.model.Operand;
import fr.canal.canal.domain.service.OperationService;

public class Division implements OperationService {
    @Override
    public double execute(Operand operand) {
        if(operand.right() == 0){
            throw new InvalidOperandException("Invalid right operand for division : division by zero");
        }
        return operand.left() / operand.right();
    }
}
