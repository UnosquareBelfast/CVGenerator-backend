package com.unosquare.cvgenerator.service;

import com.unosquare.cvgenerator.dao.HelloRepository;
import com.unosquare.cvgenerator.exception.IdDoesNotExistException;
import com.unosquare.cvgenerator.model.dto.GreetingDTO;
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
    private final ModelMapper modelMapper;

    @Override
    public GreetingDTO findById(Integer id) {
        Optional<Greeting> result = helloRepository.findById(id);
        if (!result.isPresent()) {
            String error = String.format("Cannot find object with id %s", id);
            log.error(error);
            throw new IdDoesNotExistException(error);
        }

        return modelMapper.map(result.get(), GreetingDTO.class);
    }
}
