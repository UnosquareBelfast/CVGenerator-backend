package com.unosquare.cvgenerator.service;

import com.unosquare.cvgenerator.dao.UserRepository;
import com.unosquare.cvgenerator.model.dto.UserDTO;
import com.unosquare.cvgenerator.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserDTO> findAll() {
        List<User> result = userRepository.findAll();
        List<UserDTO> resultDTO = new ArrayList<>();

        for (User user : result) {
            resultDTO.add(modelMapper.map(user, UserDTO.class));
        }

        return resultDTO;
    }
}
