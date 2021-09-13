package com.nnk.springboot.controllers;

import com.nnk.springboot.annotation.UniqueValidator;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.TradeService;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    private static List<User> userList;

    @BeforeEach
    void setUpPerTest() {
        userList = new ArrayList<>();
        userList.add(new User("test 1","test 1","ROLE_ADMIN"));
        userList.add(new User("test 2","test 2","ROLE_USER"));
    }

    @Test
    @WithAnonymousUser
    void test_home_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_addForm_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_validate_shouldReturnUnauthorized() throws Exception {
        User user = new User("test","test","ROLE_USER");
        mockMvc.perform(post("/user/validate").flashAttr("user",user))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_showUpdateForm_shouldReturnUnauthorized() throws Exception {
        User user = new User("test","test","ROLE_USER");
        when(userService.findById(anyInt())).thenReturn(user);
        mockMvc.perform(get("/user/update/1").flashAttr("user",user))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_update_shouldReturnUnauthorized() throws Exception {
        User user = new User("test","test","ROLE_USER");
        when(userService.findById(anyInt())).thenReturn(user);
        mockMvc.perform(post("/user/update/1").flashAttr("user",user))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_delete_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/user/delete/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void test_home() throws Exception {
        when(userService.findAll()).thenReturn(userList);
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"));
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void test_addForm() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void test_validate() throws Exception {
        User user = new User("test","test","ROLE_USER");
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByFullName(anyString())).thenReturn(false);
        
        mockMvc.perform(post("/user/validate").flashAttr("user",user))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void test_validate_withInvalidField() throws Exception {
        User user = new User(null,"test","ROLE_USER");
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByFullName(anyString())).thenReturn(false);
        mockMvc.perform(post("/user/validate").flashAttr("user",user))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void test_showUpdateForm() throws Exception {
        User user = new User("test","test","ROLE_USER");
        when(userService.findById(anyInt())).thenReturn(user);
        mockMvc.perform(get("/user/update/1").flashAttr("user",user))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void test_update() throws Exception {
        User user = new User("test","test","ROLE_USER");
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByFullName(anyString())).thenReturn(false);
        when(userService.findById(anyInt())).thenReturn(user);
        mockMvc.perform(post("/user/update/1").flashAttr("trade",user))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void test_update_withInvalidField() throws Exception {
        User user = new User(null,"test","ROLE_USER");
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByFullName(anyString())).thenReturn(false);
        when(userService.findById(anyInt())).thenReturn(user);
        mockMvc.perform(post("/user/update/1").flashAttr("user",user))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void test_delete() throws Exception {
        mockMvc.perform(get("/user/delete/1"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles="USER")
    void test_home_expectForbiddenAccess() throws Exception {
        when(userService.findAll()).thenReturn(userList);
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles="USER")
    void test_addForm_expectForbiddenAccess() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles="USER")
    void test_validate_expectForbiddenAccess() throws Exception {
        User user = new User("test","test","ROLE_USER");
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByFullName(anyString())).thenReturn(false);
        mockMvc.perform(post("/user/validate").flashAttr("user",user))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles="USER")
    void test_showUpdateForm_expectForbiddenAccess() throws Exception {
        User user = new User("test","test","ROLE_USER");
        when(userService.findById(anyInt())).thenReturn(user);
        mockMvc.perform(get("/user/update/1").flashAttr("user",user))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles="USER")
    void test_update_expectForbiddenAccess() throws Exception {
        User user = new User("test","test","ROLE_USER");
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByFullName(anyString())).thenReturn(false);
        when(userService.findById(anyInt())).thenReturn(user);
        mockMvc.perform(post("/user/update/1").flashAttr("trade",user))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles="USER")
    void test_delete_expectForbiddenAccess() throws Exception {
        mockMvc.perform(get("/user/delete/1"))
                .andExpect(status().isForbidden());
    }

}
