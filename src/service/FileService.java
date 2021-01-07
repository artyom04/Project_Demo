package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * {@code FileService} class
 *
 * @author Artyom
 */
public class FileService {
    /**
     * Reads from file by given path, and returns array of lines
     *
     * @param path parameter of type {@code String}
     * @return array of {@code String}s
     * @throws Exception if can't read from file
     */
    public static String[] read(String path) throws Exception {
        return Files.readAllLines(Paths.get(path)).toArray(new String[0]);
    }

    /**
     * Writes the given data to the file by given path
     *
     * @param path parameter of type {@code String}
     * @param data parameter of type {@code String}
     * @throws IOException if can't write to file
     */
    public static void write(String path, String data) throws IOException {
        Files.write(Paths.get(path), data.getBytes(), StandardOpenOption.APPEND);
    }
}