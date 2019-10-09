package com.unosquare.cvgenerator.controller;

import com.unosquare.cvgenerator.entity.Greeting;
import com.unosquare.cvgenerator.service.GreetingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class HelloController {

    private GreetingService greetingService;

    public HelloController(GreetingService theGreetingService) {
        greetingService = theGreetingService;
    }

    @GetMapping("/hello")
    public String sayHello(Model theModel) {
        Greeting greeting = greetingService.findById(1);
        theModel.addAttribute("greeting", greeting);

        return "helloworld";
    }

}
