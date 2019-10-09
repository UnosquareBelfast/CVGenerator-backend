package com.unosquare.cvgenerator.service;

import com.unosquare.cvgenerator.dao.HelloRepository;
import com.unosquare.cvgenerator.entity.Greeting;
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

    @MockBean
    private HelloRepository repository;

    @Test
    public void findById_whenValidIdPassed_returnsDto() {
        // arrange
        Greeting testGreeting = new Greeting(1, "Hello World!");
        when(repository.findById(1)).thenReturn(Optional.of(testGreeting));

        // act
        Greeting actualGreeting = greetingService.findById(1);

        // assert
        Assert.assertEquals(testGreeting, actualGreeting);
        Mockito.verify(repository, times(1)).findById(1);
    }

    @Test(expected = RuntimeException.class)
    public void findById_whenInvalidIdPassed_throwsException() {
        greetingService.findById(2);
    }
}