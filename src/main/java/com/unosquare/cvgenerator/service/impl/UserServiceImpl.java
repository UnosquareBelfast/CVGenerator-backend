package com.unosquare.cvgenerator.service.impl;

import com.unosquare.cvgenerator.dao.UserRepository;
import com.unosquare.cvgenerator.model.dto.UserDTO;
import com.unosquare.cvgenerator.model.entity.User;
import com.unosquare.cvgenerator.service.UserService;
import com.unosquare.cvgenerator.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    @Override
    public List<UserDTO> findAll() {
        List<User> result = userRepository.findAll();
        return mapperUtil.map(result, UserDTO.class);
    }

    @Override
    public Optional<UserDTO> findById(Integer id) {
        return userRepository.findById(id)
                .map(user -> mapperUtil.map(user, UserDTO.class));
    }

}
