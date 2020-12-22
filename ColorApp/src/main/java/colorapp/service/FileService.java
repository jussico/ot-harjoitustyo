package colorapp.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileService {

    /**
     * read text file
     * @param x
     * @return
     */
    public List<String> readFile(File x) {

        Path path = x.toPath();
        List<String> rawFileLines;
        try {
            rawFileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return rawFileLines;
    }

    /**
     * write text file
     * @param x
     * @param lines
     */
    public void writeFile(File x, String lines) {

        Path path = x.toPath();
        try {
            Files.writeString(path, lines, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
