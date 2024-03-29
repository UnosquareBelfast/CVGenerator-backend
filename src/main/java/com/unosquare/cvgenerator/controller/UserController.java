package com.unosquare.cvgenerator.controller;

import com.unosquare.cvgenerator.model.view.UserView;
import com.unosquare.cvgenerator.service.UserService;
import com.unosquare.cvgenerator.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserView>> getUsers() {
        List<UserView> resultListView = mapperUtil.map(userService.findAll(), UserView.class);
        if (resultListView.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultListView);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserView> getUser(@PathVariable("id") Integer id) {
        return userService.findById(id)
                .map(result -> mapperUtil.map(result, UserView.class))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

}
