package capstone.recipable.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApi {
    @GetMapping("/health-check")
    public String hello() {
        return "Hello, Recipable!";
    }

    @GetMapping("/")
    public String test() {
        return "Hello!";
    }
}