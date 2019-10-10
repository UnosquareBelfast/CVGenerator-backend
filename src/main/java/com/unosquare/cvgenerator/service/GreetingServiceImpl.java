package com.unosquare.cvgenerator.service;

import com.unosquare.cvgenerator.dao.HelloRepository;
import com.unosquare.cvgenerator.model.dto.GreetingModelView;
import com.unosquare.cvgenerator.model.entity.Greeting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {

    private final HelloRepository helloRepository;

    @Override
    public GreetingModelView findById(int id) {
        Optional<Greeting> result = helloRepository.findById(id);
        if (!result.isPresent()) {
            throw new RuntimeException(String.format("Cannot find object with id %s", id));
        }

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(result.get(), GreetingModelView.class);
    }
}
