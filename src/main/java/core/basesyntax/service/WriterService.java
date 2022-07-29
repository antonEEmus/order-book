package core.basesyntax.service;

import java.util.List;

public interface WriterService {
    void write(List<String> data, String fileName);

    default void write(String data, String fileName) {
        write(List.of(data), fileName);
    }
}
