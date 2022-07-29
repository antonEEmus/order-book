package core.basesyntax.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import core.basesyntax.model.OperationType;
import core.basesyntax.strategy.OperationHandlerStrategy;

public class ProcessorServiceImpl implements ProcessorService {
    private final OperationHandlerStrategy operationHandlerStrategy;

    public ProcessorServiceImpl(OperationHandlerStrategy operationHandlerStrategy) {
        this.operationHandlerStrategy = operationHandlerStrategy;
    }

    @Override
    public List<String> process(List<String> rawData) {
        List<String> output = new ArrayList<>();
        for (String row : rawData) {
            String[] dataRow = row.split(",");
            OperationType type = Arrays.stream(OperationType.values())
                    .filter(n -> n.getType().equals(dataRow[0]))
                    .findFirst().get();
            String result = operationHandlerStrategy.getOperationHandler(type).commitOperation(dataRow);
            if (result != null) {
                output.add(result);
            }
        }
        return output;
    }
}
