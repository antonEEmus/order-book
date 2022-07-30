package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.model.Order;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void update(Order.OrderType type, int price, int size) {
        getOrder(price).orElseGet(() -> {
                    Order order = new Order(type, price, size);
                    Storage.orderBook.add(order);
                    return order;
        }).setSize(size);
    }

    @Override
    public Integer get(int price) {
        return getOrder(price).map(Order::getSize).orElse(0);
    }

    @Override
    public Order getMax(Order.OrderType type) {
        return Storage.orderBook.stream()
                .filter(n -> n.getType().equals(type) && n.getSize() > 0)
                .max(Comparator.comparingInt(Order::getPrice))
                .orElseThrow(() -> new NoSuchElementException(
                        "No " + type.getType() + "s with size > 0 present"));
    }

    @Override
    public Order getMin(Order.OrderType type) {
        return Storage.orderBook.stream()
                .filter(n -> n.getType().equals(type) && n.getSize() > 0)
                .min(Comparator.comparingInt(Order::getPrice))
                .orElseThrow(() -> new NoSuchElementException(
                        "No " + type.getType() + "s with size > 0 present"));
    }

    @Override
    public void clear() {
        Storage.orderBook.clear();
    }

    private Optional<Order> getOrder(int price) {
        return Storage.orderBook.stream()
                .filter(n -> n.getPrice() == price)
                .findFirst();
    }
}
