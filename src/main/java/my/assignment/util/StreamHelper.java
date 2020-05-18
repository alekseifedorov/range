package my.assignment.util;

import my.assignment.exception.MyException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public final class StreamHelper {

    private StreamHelper() {
    }

    /**
     * Streams a file into an OutputStream
     */
    public static void streamFileIntoOutputStream(String filename, OutputStream os, UnaryOperator<String> converter) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(line -> {
                try {
                    String convertedLine = converter.apply(line);
                    os.write(convertedLine.getBytes());
                    os.write("\n".getBytes());
                    os.flush();
                } catch (IOException e) {
                    throw new MyException("Write error", e);
                }
            });
        } catch (IOException e) {
            throw new MyException("Invalid path", e);
        }
    }
}
