package com.unosquare.cvgenerator.controller;

import com.unosquare.cvgenerator.model.dto.UserDTO;
import com.unosquare.cvgenerator.model.entity.User;
import com.unosquare.cvgenerator.model.view.UserView;
import com.unosquare.cvgenerator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @CrossOrigin
    @GetMapping("/users")
    public List<UserView> getUsers() {
        List<UserDTO> result = userService.findAll();
        List<UserView> resultView = new ArrayList<>();

        for (UserDTO userDTO : result) {
            resultView.add(modelMapper.map(userDTO, UserView.class));
        }

        return resultView;
    }


}
