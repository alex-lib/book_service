package com.app.book_service.web.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DefaultController {

    @GetMapping
    public String redirectToSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}