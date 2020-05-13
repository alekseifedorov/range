package my.assignment.controller;

import lombok.AllArgsConstructor;
import my.assignment.service.NumberRangeService;
import my.assignment.util.StreamHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping
@AllArgsConstructor
public class NumberRangeController {

    private final NumberRangeService numberRangeService;

    @GetMapping("convert")
    public StreamingResponseBody convert(HttpServletResponse response, @RequestParam String path) throws UnsupportedEncodingException {
        String decodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8.name());
        response.setContentType("text/plain");
        response.setHeader(
                "Content-Disposition",
                "attachment;filename=sample-output.txt");

        return os -> StreamHelper.streamFileIntoOutputStream(decodedPath, os, line -> numberRangeService.convert(line));
    }
}
