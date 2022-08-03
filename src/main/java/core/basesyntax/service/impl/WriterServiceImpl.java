package core.basesyntax.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import core.basesyntax.service.WriterService;

public class WriterServiceImpl implements WriterService {
    @Override
    public void write(List<String> data, String fileName) {
        try {
            Files.writeString(Path.of(fileName), String.join(System.lineSeparator(), data));
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file: " + fileName, e);
        }
    }
}
