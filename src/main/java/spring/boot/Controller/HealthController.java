package spring.boot.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    
    @GetMapping("/")
    public String root() {
        return "Application is running!";
    }
    
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
