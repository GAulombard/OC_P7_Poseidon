package com.nnk.springboot.services;

import com.nnk.springboot.annotation.UniqueValidator;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.AlreadyExistsException;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private static List<User> userList;

    @BeforeEach
    void setUpPerTest() {
        userList = new ArrayList<>();
        userList.add(new User("Test 1","Test 1","ROLE_USER"));
        userList.add(new User("Test 2","Test 2","ROLE_ADMIN"));
    }

    @Test
    void test_findAll() {
        when(userRepository.findAll()).thenReturn(userList);
        userService.findAll();
        verify(userRepository).findAll();
    }

    @Test
    void test_save() throws AlreadyExistsException {
        User user = new User("Test","Test","ROLE_ADMIN");
        user.setPassword("password");
        when(passwordEncoder.encode((anyString()))).thenReturn("password");
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);
        userService.save(user);
        verify(userRepository).save(user);
    }

    @Test
    void test_save_shouldThrowUsernameAlreadyExistsException() throws AlreadyExistsException {
        User user = new User("Test","Test","ROLE_ADMIN");
        user.setPassword("password");
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);
        assertThrows(AlreadyExistsException.class,() -> userService.save(user));
    }

    @Test
    void test_update() throws NotFoundException {
        User user = new User("Test","Test","ROLE_ADMIN");
        user.setPassword("password");
        when(passwordEncoder.encode((anyString()))).thenReturn("password");
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);
        when(userRepository.save(user)).thenReturn(user);
        userService.update(user);
        verify(userRepository).save(user);
    }
    @Test
    void test_update_shouldThrowsNotFoundException() throws NotFoundException {
        User user = new User("Test","Test","ROLE_ADMIN");
        user.setPassword("password");
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
        assertThrows(NotFoundException.class,()->userService.update(user));
    }
    @Test
    void test_findById() throws NotFoundException {
        User user = new User("Test","Test","ROLE_ADMIN");
        when(userRepository.existsById(anyInt())).thenReturn(true);
        when(userRepository.getOne(anyInt())).thenReturn(user);
        userService.findById(anyInt());
        verify(userRepository).getOne(anyInt());
    }

    @Test
    void test_findById_shouldThrowNotFoundException() throws NotFoundException {
        User user = new User("Test","Test","ROLE_ADMIN");
        when(userRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(NotFoundException.class,() -> userService.findById(anyInt()));
    }

    @Test
    void test_deleteById() throws NotFoundException {
        when(userRepository.existsById(1)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1);
        userService.deleteById(1);
        verify(userRepository).deleteById(1);
    }

    @Test
    void test_deleteById_shouldThrowsNotFoundException() throws NotFoundException {
        when(userRepository.existsById(1)).thenReturn(false);
        assertThrows(NotFoundException.class,() -> userService.deleteById(1));
    }
}
