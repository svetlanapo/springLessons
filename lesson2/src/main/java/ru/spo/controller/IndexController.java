package ru.spo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
public class IndexController {

    @GetMapping("/mypage")
    public String index(Model model){
        model.addAttribute("message", "My message plus random UUID 1-> " + UUID.randomUUID());
        return "index";
    }

    @GetMapping("/hello")
    public String index2(Model model){
        model.addAttribute("message", "HELLO " + UUID.randomUUID());
        return "index";
    }



}