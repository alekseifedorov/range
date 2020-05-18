package my.assignment.controller;

import my.assignment.service.NumberRangeService;
import my.assignment.util.StreamHelper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class NumberRangeController {

    private NumberRangeService numberRangeService;

    @Autowired
    public NumberRangeController(NumberRangeService numberRangeService) {
        this.numberRangeService = numberRangeService;
    }

    @GetMapping("convert")
    public StreamingResponseBody convert(HttpServletResponse response, @RequestParam String path) throws UnsupportedEncodingException {
        String decodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8.name());
        response.setContentType("text/plain");
        response.setHeader(
                "Content-Disposition",
                "attachment;filename=sample-output.txt");

        return os -> StreamHelper.streamFileIntoOutputStream(decodedPath, os, numberRangeService::convert);
    }
}
