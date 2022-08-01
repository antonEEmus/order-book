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
        String output;
        if (rawData[1].equals("best_bid")) {
            Order order = orderDao.getMax(Order.OrderType.BID);
            output = order.getPrice() + "," + order.getSize();
        } else if (rawData[1].equals("best_ask")) {
            Order order = orderDao.getMin(Order.OrderType.ASK);
            output = order.getPrice() + "," + order.getSize();
        } else {
            output = String.valueOf(orderDao.get(Integer.parseInt(rawData[2])));
        }
        queries.add(output);
    }
}
