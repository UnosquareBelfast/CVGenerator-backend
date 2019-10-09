package com.unosquare.cvgenerator.service;

import com.unosquare.cvgenerator.dao.HelloRepository;
import com.unosquare.cvgenerator.entity.Greeting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
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
    public void findById_invalid() {
        greetingService.findById(2);
    }
}