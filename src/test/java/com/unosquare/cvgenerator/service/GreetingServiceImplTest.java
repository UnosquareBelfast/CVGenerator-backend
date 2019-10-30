package com.unosquare.cvgenerator.service;

import com.unosquare.cvgenerator.dao.HelloRepository;
import com.unosquare.cvgenerator.exception.IdNotFoundException;
import com.unosquare.cvgenerator.model.dto.GreetingDTO;
import com.unosquare.cvgenerator.model.entity.Greeting;
import com.unosquare.cvgenerator.util.MapperUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingServiceImplTest {

    @Autowired
    private GreetingService greetingService;

    @Autowired
    private MapperUtil mapperUtil;

    @MockBean
    private HelloRepository repository;

    @Test
    public void findById_whenValidIdPassed_returnsDto() {
        // arrange
        Greeting testGreeting = new Greeting(1, "Hello World!");
        when(repository.findById(1)).thenReturn(Optional.of(testGreeting));
        GreetingDTO testGDTO = mapperUtil.map(testGreeting, GreetingDTO.class);

        // act
        GreetingDTO actualGDTO = greetingService.findById(1);

        // assert
        Assert.assertEquals(testGDTO, actualGDTO);
        Mockito.verify(repository, times(1)).findById(1);
    }

    @Test(expected = IdNotFoundException.class)
    public void findById_whenInvalidIdPassed_throwsException() {
        greetingService.findById(2);
    }
}