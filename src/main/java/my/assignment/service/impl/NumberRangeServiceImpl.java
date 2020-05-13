package my.assignment.service.impl;

import my.assignment.exception.MyException;
import my.assignment.service.NumberRangeService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Converts a sequence of integers into ranges
 */
@Service
public class NumberRangeServiceImpl implements NumberRangeService {

    @Override
    public String convert(String line) {
        if (Strings.isBlank(line)) {
            return "";
        }

        List<Integer> numbers;
        try {
            numbers = Arrays.stream(line.trim().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new MyException("Invalid line", e);
        }

        StringBuilder sb = new StringBuilder();
        int start = numbers.get(0);
        int end = start;
        for (int current : numbers) {
            if (current - end <= 1) {
                end = current;
            } else {
                addRange(sb, start, end);
                start = end = current;
            }
        }
        addRange(sb, start, end);

        return sb.toString();
    }

    @Override
    public void streamFileIntoOutputStream(String filename, OutputStream os) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(line -> {
                try {
                    String convertedLine = convert(line);
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

    private void addRange(StringBuilder sb, int start, int end) {
        if (start >= end) {
            return;
        }

        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(start);
        sb.append('-');
        sb.append(end);
    }
}
