package core.basesyntax.strategy.handler;

import java.util.List;
import core.basesyntax.dao.OrderDao;
import core.basesyntax.model.OrderType;

public class QueryOperationHandler implements OperationHandler {
    private final OrderDao orderDao;

    public QueryOperationHandler(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void commitOperation(String[] rawData, List<String> queries) {
        if (rawData[1].equals("best_bid")) {
            queries.add(orderDao.getMax(OrderType.BID).getKey()
                    + "," + orderDao.getMax(OrderType.BID).getValue());
            return;
        }
        if (rawData[1].equals("best_ask")) {
            queries.add(orderDao.getMin(OrderType.ASK).getKey()
                    + "," + orderDao.getMin(OrderType.ASK).getValue());
            return;
        }
        int price = Integer.parseInt(rawData[2]);
        queries.add(String.valueOf(orderDao.get(OrderType.BID, price).orElseGet(
                () -> orderDao.get(OrderType.ASK, price).orElseGet(
                        () -> orderDao.get(OrderType.SPREAD, price).orElse(0)))));
    }
}
