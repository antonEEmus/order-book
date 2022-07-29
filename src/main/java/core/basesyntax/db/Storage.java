package core.basesyntax.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import core.basesyntax.model.Order;
import core.basesyntax.model.OrderType;

public class Storage {
    public static Map<OrderType, Map<Integer, Integer>> orderBook = new HashMap<>();
    public static List<Order> book = new ArrayList<>();
    public static Map<Integer, Integer> askStorage = new HashMap<>();
    public static Map<Integer, Integer> bidStorage = new HashMap<>();
    public static Map<Integer, Integer> spreadStorage = new HashMap<>();

    static {
        orderBook.put(OrderType.SPREAD, new HashMap<>());
        orderBook.put(OrderType.ASK, new HashMap<>());
        orderBook.put(OrderType.BID, new HashMap<>());
    }
}
