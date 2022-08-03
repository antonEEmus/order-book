package core.basesyntax.service.impl;

import core.basesyntax.model.OperationType;
import core.basesyntax.service.ProcessorService;
import core.basesyntax.strategy.OperationHandlerStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ProcessorServiceImpl implements ProcessorService {
    private static final String SEPARATOR = ",";
    private static final int TYPE_INDEX = 0;

    private final OperationHandlerStrategy operationHandlerStrategy;

    public ProcessorServiceImpl(OperationHandlerStrategy operationHandlerStrategy) {
        this.operationHandlerStrategy = operationHandlerStrategy;
    }

    @Override
    public List<String> process(List<String> rawData) {
        List<String> output = new ArrayList<>();
        for (String row : rawData) {
            String[] dataRow = row.split(SEPARATOR);
            OperationType type = Arrays.stream(OperationType.values())
                    .filter(n -> n.getType().equals(dataRow[TYPE_INDEX]))
                    .findFirst().orElseThrow(() -> new NoSuchElementException(
                            "Unsupported operation type: " + dataRow[TYPE_INDEX]));
            operationHandlerStrategy.getOperationHandler(type)
                    .commitOperation(dataRow, output);
        }
        return output;
    }
}
