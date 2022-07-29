package core.basesyntax.strategy.handler;

import core.basesyntax.dao.OrderDao;
import core.basesyntax.model.OrderType;

public class QueryOperationHandler implements OperationHandler {
    private final OrderDao orderDao;

    public QueryOperationHandler(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public String commitOperation(String[] rawData) {
        if (rawData[1].equals("best_bid")) {
            return orderDao.getMax(OrderType.BID).getKey()
                    + "," + orderDao.getMax(OrderType.BID).getValue();
        } else if (rawData[1].equals("best_ask")) {
            return orderDao.getMin(OrderType.ASK).getKey()
                    + "," + orderDao.getMin(OrderType.ASK).getValue();
        }
        int price = Integer.parseInt(rawData[2]);
        return String.valueOf(orderDao.get(OrderType.BID, price).orElseGet(
                () -> orderDao.get(OrderType.ASK, price).orElseGet(
                        () -> orderDao.get(OrderType.SPREAD, price).orElse(0))));
    }
}
