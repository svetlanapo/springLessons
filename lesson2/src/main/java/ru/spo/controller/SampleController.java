package ru.spo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/example1")
public class SampleController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model){
        return "index";
    }


    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    public String bye(Model model){
        model.addAttribute("message", "Welcome to bloody enterprise");
        return "index";
    }

    @RequestMapping(value = "/path/{value}", method = RequestMethod.GET)
    public String byPath(Model model, @PathVariable String value){
        model.addAttribute("message", "Welcome path variable -> " + value);
        return "index";
    }

}
