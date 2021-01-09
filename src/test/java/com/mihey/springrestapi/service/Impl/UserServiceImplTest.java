package com.mihey.springrestapi.service.Impl;

import com.mihey.springrestapi.model.Role;
import com.mihey.springrestapi.model.Status;
import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.repository.UserRepository;
import com.mihey.springrestapi.repository.WriterRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @MockBean
    private UserRepository userRepository;

    User user = new User();
    {
        user.setId(1);
        user.setUsername("User");
        user.setPassword("User");
        user.setPhoneNumber("+74999999999");
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
    }
    @Test
    void getUsersTest() {
        List<User> users = new ArrayList<>();
        users.add(user);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        assertEquals(users, userService.getUsers());
        assertNotEquals(0, userService.getUsers().size());
    }

    @Test
    void getUserByIdTest() {
        Optional<User> u = Optional.of(user);
        Mockito.when(userRepository.findById(anyInt())).thenReturn(u);
        assertEquals("User", userService.getUserById(1).getUsername());
        assertNotEquals("Ivan", userService.getUserById(1).getUsername());
    }

    @Test
    void saveUserTest() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.saveUser(user));
        assertNotEquals("Jon", userService.saveUser(user).getUsername());
    }

    @Test
    void updateUserTest() {
        user.setUsername("Ivan");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.saveUser(user));
        assertEquals("Ivan", userService.saveUser(user).getUsername());
        assertNotEquals("Jon", userService.saveUser(user).getUsername());
    }

    @Test
    void deleteUserByIdTest() {
        Mockito.doNothing().when(userRepository).deleteById(anyInt());
        userService.deleteUserById(1);
    }
}