package core.basesyntax.strategy;

import core.basesyntax.model.OrderType;

public interface OrderStrategy {
    OrderType getOrderType(String string);
}
