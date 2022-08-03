package core.basesyntax;

import core.basesyntax.dao.OrderDao;
import core.basesyntax.dao.OrderDaoImpl;
import core.basesyntax.model.OperationType;
import core.basesyntax.service.FileCreatorService;
import core.basesyntax.service.impl.FileCreatorServiceImpl;
import core.basesyntax.service.ProcessorService;
import core.basesyntax.service.impl.ProcessorServiceImpl;
import core.basesyntax.service.ReaderService;
import core.basesyntax.service.impl.ReaderServiceImpl;
import core.basesyntax.service.WriterService;
import core.basesyntax.service.impl.WriterServiceImpl;
import core.basesyntax.strategy.OperationHandlerStrategy;
import core.basesyntax.strategy.OperationHandlerStrategyImpl;
import core.basesyntax.strategy.handler.OperationHandler;
import core.basesyntax.strategy.handler.OrderOperationHandler;
import core.basesyntax.strategy.handler.QueryOperationHandler;
import core.basesyntax.strategy.handler.UpdateOperationHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String INPUT_FILE_NAME = "input.txt";
    private static final String OUTPUT_FILE_NAME = "output.txt";

    public static void main(String[] args) {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.clear();

        Map<OperationType, OperationHandler> operationHandlerMap = new HashMap<>();
        operationHandlerMap.put(OperationType.UPDATE, new UpdateOperationHandler(orderDao));
        operationHandlerMap.put(OperationType.ORDER, new OrderOperationHandler(orderDao));
        operationHandlerMap.put(OperationType.QUERY, new QueryOperationHandler(orderDao));

        ReaderService readerService = new ReaderServiceImpl();
        List<String> rawData = readerService.read(INPUT_FILE_NAME);

        OperationHandlerStrategy operationHandlerStrategy
                = new OperationHandlerStrategyImpl(operationHandlerMap);

        ProcessorService processorService = new ProcessorServiceImpl(operationHandlerStrategy);
        List<String> data = processorService.process(rawData);

        FileCreatorService fileCreatorService = new FileCreatorServiceImpl();
        fileCreatorService.createFile(OUTPUT_FILE_NAME);

        WriterService writerService = new WriterServiceImpl();
        writerService.write(data, OUTPUT_FILE_NAME);
    }
}
