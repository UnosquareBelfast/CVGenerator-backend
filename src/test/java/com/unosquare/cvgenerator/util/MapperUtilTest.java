package com.unosquare.cvgenerator.util;

import com.unosquare.cvgenerator.model.dto.UserDTO;
import com.unosquare.cvgenerator.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperUtilTest {

    @Autowired
    private MapperUtil mapperUtil;

    @Test
    public void map() {

        // arrange
        User user1 = new User(1, "Bobby", "Firmino", "bobby@yahoo.com");
        UserDTO userDtoExpected = new UserDTO();
        userDtoExpected.setId(1);
        userDtoExpected.setFirstName("Bobby");
        userDtoExpected.setLastName("Firmino");

        // act
        UserDTO userDtoActual = mapperUtil.map(user1, UserDTO.class);

        // assert
        Assert.assertEquals(userDtoExpected, userDtoActual);
    }

    @Test
    public void mapAll() {

        // arrange
        User user1 = new User(1, "Bobby", "Firmino", "bobby@yahoo.com");
        User user2 = new User(2, "Phil", "Jones", "p.jones@hotmail.com");
        List<User> testListUser = Arrays.asList(user1, user2);

        UserDTO userDto1 = new UserDTO();
        userDto1.setId(1);
        userDto1.setFirstName("Bobby");
        userDto1.setLastName("Firmino");

        UserDTO userDto2 = new UserDTO();
        userDto2.setId(2);
        userDto2.setFirstName("Phil");
        userDto2.setLastName("Jones");

        List<UserDTO> testListUserDto = Arrays.asList(userDto1, userDto2);

        // act
        List<UserDTO> testListActual = mapperUtil.map(testListUser, UserDTO.class);

        // assert
        Assert.assertEquals(testListUserDto, testListActual);
    }
}