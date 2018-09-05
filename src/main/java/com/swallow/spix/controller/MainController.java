package com.swallow.spix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(path = "/home")
    public String mainPage(Model model) {
        return "main";
    }

}
