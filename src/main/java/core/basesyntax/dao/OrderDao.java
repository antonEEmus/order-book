package core.basesyntax.dao;

import java.util.Map;
import java.util.Optional;
import core.basesyntax.model.OrderType;

public interface OrderDao {
    void update(OrderType type, int price, int size);

    Optional<Integer> get(OrderType type, int price);

    Map.Entry<Integer, Integer> getMax(OrderType type);

    Map.Entry<Integer, Integer> getMin(OrderType type);
}
