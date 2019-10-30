package com.unosquare.cvgenerator.util;

import com.flextrade.jfixture.JFixture;
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
    public void map_singleObject() {

        // arrange
        JFixture fixture = new JFixture();
        User user1 = fixture.create(User.class);
        UserDTO userDtoExpected = fixture.create(UserDTO.class);
        userDtoExpected.setId(user1.getId());
        userDtoExpected.setFirstName(user1.getFirstName());
        userDtoExpected.setLastName(user1.getLastName());

        // act
        UserDTO userDtoActual = mapperUtil.map(user1, UserDTO.class);

        // assert
        Assert.assertEquals(userDtoExpected, userDtoActual);
    }

    @Test
    public void map_objectList() {

        // arrange
        JFixture fixture = new JFixture();
        User user1 = fixture.create(User.class);
        User user2 = fixture.create(User.class);
        List<User> testListUser = Arrays.asList(user1, user2);

        UserDTO userDto1 = new UserDTO();
        userDto1.setId(user1.getId());
        userDto1.setFirstName(user1.getFirstName());
        userDto1.setLastName(user1.getLastName());

        UserDTO userDto2 = new UserDTO();
        userDto2.setId(user2.getId());
        userDto2.setFirstName(user2.getFirstName());
        userDto2.setLastName(user2.getLastName());

        List<UserDTO> testListUserDto = Arrays.asList(userDto1, userDto2);

        // act
        List<UserDTO> testListActual = mapperUtil.map(testListUser, UserDTO.class);

        // assert
        Assert.assertEquals(testListUserDto, testListActual);
    }
}