package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.model.OrderType;
import java.util.Comparator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void update(OrderType type, int price, int size) {
        Storage.orderBook.get(type).put(price, size);
    }

    @Override
    public Optional<Integer> get(OrderType type, int price) {
        return Optional.ofNullable(Storage.orderBook.get(type).get(price));
    }

    @Override
    public Map.Entry<Integer, Integer> getMax(OrderType type) {
        return Storage.orderBook.get(type).entrySet().stream()
                .filter(n -> n.getValue() > 0)
                .max(Comparator.comparingInt(Map.Entry::getKey))
                .orElseThrow(() -> new NoSuchElementException(
                        "No " + type.getType() + "s with size > 0 present"));
    }

    @Override
    public Map.Entry<Integer, Integer> getMin(OrderType type) {
        return Storage.orderBook.get(type).entrySet().stream()
                .filter(n -> n.getValue() > 0)
                .min(Comparator.comparingInt(Map.Entry::getKey))
                .orElseThrow(() -> new NoSuchElementException(
                        "No " + type.getType() + "s with size > 0 present"));
    }
}
