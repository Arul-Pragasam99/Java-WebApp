package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final List<String> messages = new ArrayList<>();

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Java Web App");
        model.addAttribute("currentTime", LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        model.addAttribute("messages", messages);
        return "index";
    }

    @PostMapping("/message")
    public String addMessage(@RequestParam String message) {
        if (message != null && !message.trim().isEmpty()) {
            messages.add(message.trim());
        }
        return "redirect:/";
    }

    @GetMapping("/api/status")
    @ResponseBody
    public String apiStatus() {
        return "{\"status\": \"running\", \"app\": \"java-webapp\", \"time\": \"" +
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\"}";
    }
}
