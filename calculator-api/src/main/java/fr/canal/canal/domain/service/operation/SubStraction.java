package fr.canal.canal.domain.service.operation;

import fr.canal.canal.domain.model.Operand;
import fr.canal.canal.domain.service.OperationService;

public class SubStraction implements OperationService {
    @Override
    public double execute(Operand operand) {
        return operand.left() - operand.right();
    }
}
