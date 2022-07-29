package core.basesyntax.db;

import core.basesyntax.model.OrderType;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    public static Map<OrderType, Map<Integer, Integer>> orderBook = new HashMap<>();

    static {
        clear();
    }

    public static void clear() {
        orderBook = new HashMap<>();
        orderBook.put(OrderType.ASK, new HashMap<>());
        orderBook.put(OrderType.BID, new HashMap<>());
    }
}
