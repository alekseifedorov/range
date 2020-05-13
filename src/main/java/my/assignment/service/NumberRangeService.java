package my.assignment.service;

import java.io.OutputStream;

public interface NumberRangeService {

    /**
     * Converts a sequence of integers represented as a text line into a sequence of ranges, e.g. 1,2,3,6,7 into 1-3 6-7.
     * @param line A String with consecutive integers to be converted into ranges
     * @return A sequence of integer ranges
     */
    String convert(String line);

    /**
     * Streams a file into output stream to use StreamingResponseBody.
     * @param filename
     * @param os
     */
    void streamFileIntoOutputStream(String filename, OutputStream os);
}
