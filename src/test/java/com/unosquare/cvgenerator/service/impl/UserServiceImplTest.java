package com.unosquare.cvgenerator.service.impl;

import com.flextrade.jfixture.JFixture;
import com.unosquare.cvgenerator.dao.UserRepository;
import com.unosquare.cvgenerator.model.dto.UserDTO;
import com.unosquare.cvgenerator.model.entity.User;
import com.unosquare.cvgenerator.service.UserService;
import com.unosquare.cvgenerator.util.MapperUtil;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private MapperUtil mapperUtil;

    @Mock
    private UserRepository repository;

    private UserService userService;

    private JFixture fixture = new JFixture();


    @Before
    public void setup() {
        userService = new UserServiceImpl(repository, mapperUtil);
    }

    @Test
    public void findAll_whenDataAvailable_returnsDtoList() {
        // arrange
        List<User> testListUser = Lists.newArrayList(fixture.collections().createCollection(User.class));
        fixture.collections().addManyTo(testListUser, User.class);
        when(repository.findAll()).thenReturn(testListUser);
        List<UserDTO> testListUserDTO = mapperUtil.map(testListUser, UserDTO.class);

        // act
        List<UserDTO> actualListUserDTO = userService.findAll();

        // assert
        Assert.assertEquals(testListUserDTO, actualListUserDTO);
        Mockito.verify(repository, times(1)).findAll();
    }

    @Test
    public void findAll_whenNoData_throwsEmptyList() {
        // arrange
        when(repository.findAll()).thenReturn(emptyList());

        // act
        List<UserDTO> actualListUserDTO = userService.findAll();

        // assert
        Assert.assertTrue(actualListUserDTO.isEmpty());
    }

    @Test
    public void findById_whenValidIdPassed_returnsDto() {
        // arrange
        User testUser = fixture.create(User.class);
        when(repository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        UserDTO testUserDTO = mapperUtil.map(testUser, UserDTO.class);

        // act
        UserDTO actualUserDTO = userService.findById(testUser.getId()).orElse(null);

        // assert
        Assert.assertEquals(testUserDTO, actualUserDTO);
        Mockito.verify(repository, times(1)).findById(testUser.getId());
    }

    @Test
    public void findById_whenInvalidIdPassed_emptyOptionalReturned() {
        // arrange
        UserDTO testUserDTO = fixture.create(UserDTO.class);
        when(repository.findById(testUserDTO.getId())).thenReturn(Optional.empty());

        // act
        Optional<UserDTO> result = userService.findById(testUserDTO.getId());

        // assert
        Assert.assertFalse(result.isPresent());
    }

}
