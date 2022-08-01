package core.basesyntax.strategy.handler;

import core.basesyntax.dao.OrderDao;
import core.basesyntax.model.Order;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class UpdateOperationHandler implements OperationHandler {
    private final OrderDao orderDao;

    public UpdateOperationHandler(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void commitOperation(String[] rawData, List<String> queries) {
        int price = Integer.parseInt(rawData[1]);
        int size = Integer.parseInt(rawData[2]);
        Order.OrderType type = Arrays.stream(Order.OrderType.values())
                .filter(n -> n.getType().equals(rawData[3]))
                .findFirst().orElseThrow(
                        () -> new NoSuchElementException("Unsupported order type: " + rawData[3]));
        orderDao.update(type, price, size);
    }
}
