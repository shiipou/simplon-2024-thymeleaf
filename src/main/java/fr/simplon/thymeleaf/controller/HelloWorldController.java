package fr.simplon.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello/world")
public class HelloWorldController {

    @GetMapping
    public String sayHello(Model model) {
        String[] names = new String[]{"John", "Jane", "Jack"};
        model.addAttribute("names", names);
        model.addAttribute("currentUser", names[0]);
        return "index";
    }
}
