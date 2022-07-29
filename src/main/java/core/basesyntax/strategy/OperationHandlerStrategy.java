package core.basesyntax.strategy;

import core.basesyntax.model.OperationType;
import core.basesyntax.strategy.handler.OperationHandler;

public interface OperationHandlerStrategy {
    OperationHandler getOperationHandler(OperationType type);
}
