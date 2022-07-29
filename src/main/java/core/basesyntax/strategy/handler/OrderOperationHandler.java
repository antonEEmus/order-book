package core.basesyntax.strategy.handler;

import java.util.List;
import core.basesyntax.dao.OrderDao;
import core.basesyntax.model.OrderType;
import core.basesyntax.strategy.OrderStrategy;

public class OrderOperationHandler implements OperationHandler {
    private final OrderStrategy orderStrategy;
    private final OrderDao orderDao;

    public OrderOperationHandler(OrderStrategy orderStrategy, OrderDao orderDao) {
        this.orderStrategy = orderStrategy;
        this.orderDao = orderDao;
    }

    @Override
    public void commitOperation(String[] rawData, List<String> queries) {
        OrderType type = orderStrategy.getOrderType(rawData[1]);
        orderDao.update(type, orderDao.getMax(type).getKey(),
                (type.equals(OrderType.BID) ? orderDao.getMax(type) : orderDao.getMin(type))
                        .getValue() - Integer.parseInt(rawData[2]));
    }
}
