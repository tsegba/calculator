package fr.canal.canal.domain.service;

import fr.canal.canal.domain.model.Operand;
import fr.canal.canal.domain.service.OperationService;

public class CalculatorFactory {
    private OperationService operationService;

    public Double execute(Operand operand){
        return operationService.execute(operand);
    }

   public OperationService applyOpration(OperationService operationService){
        this.operationService = operationService;
        return this.operationService;
    }
}
