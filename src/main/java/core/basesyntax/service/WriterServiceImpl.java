package core.basesyntax.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WriterServiceImpl implements WriterService {
    @Override
    public void write(List<String> data, String fileName) {
        try {
            Files.write(Path.of(fileName), String.join("\n",
                    data).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file: " + fileName, e);
        }
    }
}
