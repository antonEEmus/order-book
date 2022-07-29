package core.basesyntax.strategy;

import java.util.Map;
import core.basesyntax.model.OperationType;
import core.basesyntax.strategy.handler.OperationHandler;

public class OperationHandlerStrategyImpl implements OperationHandlerStrategy {
    private final Map<OperationType, OperationHandler> operationHandlerMap;

    public OperationHandlerStrategyImpl(Map<OperationType, OperationHandler> operationHandlerMap) {
        this.operationHandlerMap = operationHandlerMap;
    }

    @Override
    public OperationHandler getOperationHandler(OperationType type) {
        return operationHandlerMap.get(type);
    }
}
