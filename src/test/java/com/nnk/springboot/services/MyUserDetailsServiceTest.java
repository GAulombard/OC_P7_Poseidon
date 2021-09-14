package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MyUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MyUserDetailsService myUserDetailsService;

    @Test
    public void test_loadUserByUserName(){
        User user = new User();
        user.setFullName("Jean Michel");
        user.setUsername("Michou");
        user.setPassword("Azerty1#");
        user.setRole("ROLE_USER");

        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(userOptional);

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getUsername());

        assertEquals("Michou",userDetails.getUsername());
    }

    @Test
    public void test_loadUserByUserName_shouldThrowsUsernameNotFoundException(){
        User user = new User();
        user.setFullName("Jean Michel");
        user.setUsername("Michou");
        user.setPassword("Azerty1#");
        user.setRole("ROLE_USER");

        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findUserByUsername(anyString())).thenThrow(UsernameNotFoundException.class);

        assertThrows(UsernameNotFoundException.class,()-> myUserDetailsService.loadUserByUsername("Michou"));
    }
}
