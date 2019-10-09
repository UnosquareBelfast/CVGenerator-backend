package com.unosquare.cvgenerator.service;

import com.unosquare.cvgenerator.dao.HelloRepository;
import com.unosquare.cvgenerator.entity.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {

    private final HelloRepository helloRepository;

    @Override
    public Greeting findById(int id) {
        Optional<Greeting> result = helloRepository.findById(id);
        if (!result.isPresent()) {
            throw new RuntimeException(String.format("Cannot find object with id %s", id));
        }

        return result.get();
    }
}
