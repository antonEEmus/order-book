package core.basesyntax.strategy.handler;

import core.basesyntax.dao.OrderDao;
import core.basesyntax.model.Order;
import java.util.List;

public class OrderOperationHandler implements OperationHandler {
    private final OrderDao orderDao;

    public OrderOperationHandler(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void commitOperation(String[] rawData, List<String> queries) {
        if (rawData[1].equals("sell")) {
            Order order = orderDao.getMax(Order.OrderType.BID);
            orderDao.update(Order.OrderType.BID, order.getPrice(),
                    order.getSize() - Integer.parseInt(rawData[2]));
            return;
        }
        if (rawData[1].equals("buy")) {
            Order order = orderDao.getMin(Order.OrderType.ASK);
            orderDao.update(Order.OrderType.ASK, order.getPrice(),
                    order.getSize() - Integer.parseInt(rawData[2]));
        }
    }
}
