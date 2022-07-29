package core.basesyntax.strategy.handler;

import java.util.Arrays;
import java.util.NoSuchElementException;
import core.basesyntax.dao.OrderDao;
import core.basesyntax.model.OrderType;

public class UpdateOperationHandler implements OperationHandler {
    private final OrderDao orderDao;

    public UpdateOperationHandler(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public String commitOperation(String[] rawData) {
        int price = Integer.parseInt(rawData[1]);
        int size = Integer.parseInt(rawData[2]);
        OrderType type = Arrays.stream(OrderType.values())
                .filter(n -> n.getType().equals(rawData[3]))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Unsupported order type"));
        orderDao.update(type, price, size);
        return null;
    }
}
