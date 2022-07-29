package core.basesyntax.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCreatorServiceImpl implements FileCreatorService {
    @Override
    public Path createFile(String fileName) {
        try {
            return Files.createFile(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Cannot create file: " + fileName, e);
        }
    }
}
