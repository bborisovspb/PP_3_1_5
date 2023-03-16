package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String user() {
        return "userinfo";
    }

    @GetMapping("/admin")
    public String admin() {
        return "adminPage";
    }

}
