package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.services.RatingService;
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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RatingController.class)
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    private static List<Rating> ratingList;

    @BeforeEach
    void setUpPerTest() {
        ratingList = new ArrayList<>();
        ratingList.add(new Rating("test 1","test 1","test 1", 1));
        ratingList.add(new Rating("test 2","test 2","test 2", 2));
    }

    @Test
    @WithAnonymousUser
    void test_home_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_addForm_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_validate_shouldReturnUnauthorized() throws Exception {
        Rating rating = new Rating("test","test","test", 0);
        mockMvc.perform(post("/rating/validate").flashAttr("rating",rating))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_showUpdateForm_shouldReturnUnauthorized() throws Exception {
        Rating rating = new Rating("test","test","test", 0);
        when(ratingService.findById(anyInt())).thenReturn(rating);
        mockMvc.perform(get("/rating/update/1").flashAttr("rating",rating))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_update_shouldReturnUnauthorized() throws Exception {
        Rating rating = new Rating("test","test","test", 0);
        when(ratingService.findById(anyInt())).thenReturn(rating);
        mockMvc.perform(post("/rating/update/1").flashAttr("rating",rating))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_delete_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/rating/delete/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void test_home() throws Exception {
        when(ratingService.findAll()).thenReturn(ratingList);
        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ratingList"));
    }

    @Test
    @WithMockUser
    void test_addForm() throws Exception {
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rating"));
    }

    @Test
    @WithMockUser
    void test_validate() throws Exception {
        Rating rating = new Rating("test","test","test", 0);
        mockMvc.perform(post("/rating/validate").flashAttr("rating",rating))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void test_validate_withInvalidField() throws Exception {
        Rating rating = new Rating(null,"test","test", 0);
        mockMvc.perform(post("/rating/validate").flashAttr("rating",rating))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser
    void test_showUpdateForm() throws Exception {
        Rating rating = new Rating("test","test","test", 0);
        when(ratingService.findById(anyInt())).thenReturn(rating);
        mockMvc.perform(get("/rating/update/1").flashAttr("rating",rating))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rating"));
    }

    @Test
    @WithMockUser
    void test_update() throws Exception {
        Rating rating = new Rating("test","test","test", 0);
        when(ratingService.findById(anyInt())).thenReturn(rating);
        mockMvc.perform(post("/rating/update/1").flashAttr("rating",rating))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void test_update_withInvalidField() throws Exception {
        Rating rating = new Rating(null,"test","test", 0);
        when(ratingService.findById(anyInt())).thenReturn(rating);
        mockMvc.perform(post("/rating/update/1").flashAttr("rating",rating))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser
    void test_delete() throws Exception {
        mockMvc.perform(get("/rating/delete/1"))
                .andExpect(status().is3xxRedirection());
    }
}
