package com.unosquare.cvgenerator.service;

import com.unosquare.cvgenerator.model.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll();
    Optional<UserDTO> findById(Integer id);

}
