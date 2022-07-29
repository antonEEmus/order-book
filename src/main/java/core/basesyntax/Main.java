package core.basesyntax;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import core.basesyntax.dao.OrderDao;
import core.basesyntax.dao.OrderDaoImpl;
import core.basesyntax.model.OperationType;
import core.basesyntax.model.OrderType;
import core.basesyntax.service.FileCreatorService;
import core.basesyntax.service.FileCreatorServiceImpl;
import core.basesyntax.service.ProcessorService;
import core.basesyntax.service.ProcessorServiceImpl;
import core.basesyntax.service.ReaderService;
import core.basesyntax.service.ReaderServiceImpl;
import core.basesyntax.service.WriterService;
import core.basesyntax.service.WriterServiceImpl;
import core.basesyntax.strategy.OperationHandlerStrategy;
import core.basesyntax.strategy.OperationHandlerStrategyImpl;
import core.basesyntax.strategy.OrderStrategy;
import core.basesyntax.strategy.OrderStrategyImpl;
import core.basesyntax.strategy.handler.OperationHandler;
import core.basesyntax.strategy.handler.OrderOperationHandler;
import core.basesyntax.strategy.handler.QueryOperationHandler;
import core.basesyntax.strategy.handler.UpdateOperationHandler;

public class Main {
    private static final String INPUT_FILE_NAME = System.getProperty("user.dir")
            + "/src/main/resources/input.txt";
    private static final String OUTPUT_FILE_NAME = System.getProperty("user.dir")
            + "/src/main/resources/output.txt";

    public static void main(String[] args) {
        Map<String, OrderType> orderTypeMap = new HashMap<>();
        orderTypeMap.put("buy", OrderType.ASK);
        orderTypeMap.put("sell", OrderType.BID);

        OrderDao orderDao = new OrderDaoImpl();
        OrderStrategy orderStrategy = new OrderStrategyImpl(orderTypeMap);

        Map<OperationType, OperationHandler> operationHandlerMap = new HashMap<>();
        operationHandlerMap.put(OperationType.UPDATE, new UpdateOperationHandler(orderDao));
        operationHandlerMap.put(OperationType.ORDER, new OrderOperationHandler(orderStrategy, orderDao));
        operationHandlerMap.put(OperationType.QUERY, new QueryOperationHandler(orderDao));

        ReaderService readerService = new ReaderServiceImpl();
        List<String> rawData = readerService.read(INPUT_FILE_NAME);

        OperationHandlerStrategy operationHandlerStrategy
                = new OperationHandlerStrategyImpl(operationHandlerMap);

        ProcessorService processorService = new ProcessorServiceImpl(operationHandlerStrategy);
        List<String> data = processorService.process(rawData);

        FileCreatorService fileCreatorService = new FileCreatorServiceImpl();
        Path outputFilePath = fileCreatorService.createFile(OUTPUT_FILE_NAME);

        WriterService writerService = new WriterServiceImpl();
        writerService.write(data, OUTPUT_FILE_NAME);
    }
}
