package com.unosquare.cvgenerator.service;

import com.unosquare.cvgenerator.dao.UserRepository;
import com.unosquare.cvgenerator.exception.IdNotFoundException;
import com.unosquare.cvgenerator.model.dto.UserDTO;
import com.unosquare.cvgenerator.model.entity.User;
import com.unosquare.cvgenerator.util.MapperUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MapperUtil mapperUtil;

    @MockBean
    private UserRepository repository;

    @Test
    public void findAll_whenDataAvailable_returnsDtoList() {

        // arrange
        User user1 = new User(1, "Bobby", "Firmino", "bobby@yahoo.com");
        User user2 = new User(2, "Phil", "Jones", "p.jones@hotmail.com");
        List<User> testListUser = Arrays.asList(user1, user2);

        when(repository.findAll()).thenReturn(testListUser);
        List<UserDTO> testListUserDto = mapperUtil.map(testListUser, UserDTO.class);

        // act
        List<UserDTO> actualListUserDto = userService.findAll();

        // assert
        Assert.assertEquals(testListUserDto, actualListUserDto);
        Mockito.verify(repository, times(1)).findAll();
    }

    @Test(expected = NullPointerException.class)
    public void findAll_whenNoData_throwsException() {
        when(repository.findAll()).thenReturn(null);
        userService.findAll();
    }

    @Test
    public void findById_whenValidIdPassed_returnsDto() {
        // arrange
        User testUser = new User(1, "Bobby", "Firmino", "bobby@yahoo.com");
        when(repository.findById(1)).thenReturn(Optional.of(testUser));
        UserDTO testUserDTO = mapperUtil.map(testUser, UserDTO.class);

        // act
        UserDTO actualUserDTO = userService.findById(1);

        // assert
        Assert.assertEquals(testUserDTO, actualUserDTO);
        Mockito.verify(repository, times(1)).findById(1);

    }

    @Test(expected = IdNotFoundException.class)
    public void findById_whenInvalidIdPassed_throwsException() {
        userService.findById(5000);
    }

}