package my.assignment.controller;

import lombok.AllArgsConstructor;
import my.assignment.exception.MyException;
import my.assignment.service.NumberRangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@RestController
@RequestMapping
@AllArgsConstructor
public class NumberRangeController {

    private final NumberRangeService numberRangeService;

    @GetMapping("convert")
    public StreamingResponseBody convert(HttpServletResponse response, @RequestParam String path) throws UnsupportedEncodingException {
        String encodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8.name());
        response.setContentType("text/plain");
        response.setHeader(
                "Content-Disposition",
                "attachment;filename=sample-output.txt");

        return os -> convert(encodedPath, os);
    }

    private void convert(String filename, OutputStream os) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(line -> {
                try {
                    String convertedLine = numberRangeService.convert(line);
                    os.write(convertedLine.getBytes());
                    os.write("\n".getBytes());
                    os.flush();
                } catch (Exception e) {
                    throw new MyException("Write error", e);
                }
            });
        }
    }
}
