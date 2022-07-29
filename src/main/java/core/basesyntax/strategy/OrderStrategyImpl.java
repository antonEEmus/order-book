package core.basesyntax.strategy;

import java.util.Map;
import core.basesyntax.model.OrderType;

public class OrderStrategyImpl implements OrderStrategy {
    private final Map<String, OrderType> orderTypeMap;

    public OrderStrategyImpl(Map<String, OrderType> orderTypeMap) {
        this.orderTypeMap = orderTypeMap;
    }

    @Override
    public OrderType getOrderType(String string) {
        return orderTypeMap.get(string);
    }
}
