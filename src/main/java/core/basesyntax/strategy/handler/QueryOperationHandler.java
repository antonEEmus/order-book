package core.basesyntax.strategy.handler;

import core.basesyntax.dao.OrderDao;
import core.basesyntax.model.Order;
import java.util.List;

public class QueryOperationHandler implements OperationHandler {
    private final OrderDao orderDao;

    public QueryOperationHandler(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void commitOperation(String[] rawData, List<String> queries) {
        if (rawData[1].equals("best_bid")) {
            Order order = orderDao.getMax(Order.OrderType.BID);
            queries.add(order.getPrice() + "," + order.getSize());
            return;
        }
        if (rawData[1].equals("best_ask")) {
            Order order = orderDao.getMin(Order.OrderType.ASK);
            queries.add(order.getPrice() + "," + order.getSize());
            return;
        }
        int price = Integer.parseInt(rawData[2]);
        queries.add(String.valueOf(orderDao.get(price)));
    }
}
