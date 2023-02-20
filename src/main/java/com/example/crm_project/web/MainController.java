package com.example.crm_project.web;

import com.example.crm_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class MainController {
    private final UserRepository userRepository;
    @GetMapping("/welcomePage")
    public String homePage() {
        return "index";
    }
}
