package core.basesyntax.dao;

import core.basesyntax.model.Order;

public interface OrderDao {
    void update(Order.OrderType type, int price, int size);

    Integer get(int price);

    Order getMax(Order.OrderType type);

    Order getMin(Order.OrderType type);

    void clear();
}
