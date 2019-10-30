package com.unosquare.cvgenerator.controller;

import com.unosquare.cvgenerator.model.dto.GreetingDTO;
import com.unosquare.cvgenerator.model.view.GreetingView;
import com.unosquare.cvgenerator.service.GreetingService;
import com.unosquare.cvgenerator.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HelloController {

    private final GreetingService greetingService;
    private final MapperUtil mapperUtil;

    @GetMapping("/hello/{id}")
    public String sayHello(@PathVariable("id") Integer id, Model theModel) {
        GreetingDTO greetingDTO = greetingService.findById(id);
        GreetingView greetingView = mapperUtil.map(greetingDTO, GreetingView.class);
        theModel.addAttribute("greetingView", greetingView);

        return "helloworld";
    }

}
