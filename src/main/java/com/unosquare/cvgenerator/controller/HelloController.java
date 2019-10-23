package com.unosquare.cvgenerator.controller;

import com.unosquare.cvgenerator.model.dto.GreetingDTO;
import com.unosquare.cvgenerator.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HelloController {

    private final GreetingService greetingService;

    @CrossOrigin
    @GetMapping("/hello/{id}")
    public String sayHello(@PathVariable("id") Integer id, Model theModel) {
        GreetingDTO greetingDTO = greetingService.findById(id);
        theModel.addAttribute("greetingDTO", greetingDTO);

        return "helloworld";
    }

}
