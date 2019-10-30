package com.unosquare.cvgenerator.controller;

import com.unosquare.cvgenerator.model.dto.UserDTO;
import com.unosquare.cvgenerator.model.view.UserView;
import com.unosquare.cvgenerator.service.UserService;
import com.unosquare.cvgenerator.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public List<UserView> getUsers() {
        List<UserDTO> resultListDTO = userService.findAll();
        return mapperUtil.map(resultListDTO, UserView.class);
    }

    @GetMapping("/{id}")
    public UserView getUser(@PathVariable("id") Integer id) {
        UserDTO resultDTO = userService.findById(id);
        return mapperUtil.map(resultDTO, UserView.class);
    }

}
