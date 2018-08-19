package com.swallow.spix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LessonsController {

    @GetMapping("/lesson_one")
    public String index() {
        return "index.html";
    }

}
