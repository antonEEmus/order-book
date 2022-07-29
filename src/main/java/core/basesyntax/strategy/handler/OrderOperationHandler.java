package core.basesyntax.strategy.handler;

import core.basesyntax.dao.OrderDao;
import core.basesyntax.model.OrderType;
import java.util.List;

public class OrderOperationHandler implements OperationHandler {
    private final OrderDao orderDao;

    public OrderOperationHandler(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void commitOperation(String[] rawData, List<String> queries) {
        OrderType type = rawData[1].equals("sell") ? OrderType.BID : OrderType.ASK;
        orderDao.update(type, orderDao.getMax(type).getKey(),
                (type.equals(OrderType.BID) ? orderDao.getMax(type) : orderDao.getMin(type))
                        .getValue() - Integer.parseInt(rawData[2]));
    }
}
