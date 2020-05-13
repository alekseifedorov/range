package my.assignment.service.impl;

import my.assignment.service.NumberRangeService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Integer> numbers = Arrays.stream(line.trim().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

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
