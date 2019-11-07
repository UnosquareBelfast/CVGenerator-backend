package com.unosquare.cvgenerator.service;

import com.unosquare.cvgenerator.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();
    UserDTO findById(Integer id);

}
