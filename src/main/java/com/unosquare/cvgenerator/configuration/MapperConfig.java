package com.unosquare.cvgenerator.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper setModelMapper() {
        return new ModelMapper();
    }
}
