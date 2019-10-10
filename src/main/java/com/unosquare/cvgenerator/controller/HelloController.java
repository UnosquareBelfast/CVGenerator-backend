package com.unosquare.cvgenerator.controller;

import com.unosquare.cvgenerator.model.dto.GreetingModelView;
import com.unosquare.cvgenerator.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class HelloController {

    private final GreetingService greetingService;

    @GetMapping("/hello")
    public String sayHello(Model theModel) {
        GreetingModelView greetingModelView = greetingService.findById(1);
        theModel.addAttribute("greeting", greetingModelView);

        return "helloworld";
    }

}