package com.unosquare.cvgenerator.configuration;

import com.unosquare.cvgenerator.util.MapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public MapperUtil mapperUtil() { return new MapperUtil(new ModelMapper()); }

}
