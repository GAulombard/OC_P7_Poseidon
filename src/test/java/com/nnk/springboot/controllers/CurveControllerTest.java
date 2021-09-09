package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.CurvePointService;
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

@WebMvcTest(CurveController.class)
public class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointService curvePointService;

    private static List<CurvePoint> curvePointList;

    @BeforeEach
    void setUpPerTest() {
        curvePointList = new ArrayList<>();
        curvePointList.add(new CurvePoint(1, 1.0, 1.0));
        curvePointList.add(new CurvePoint(2, 2.0, 2.0));
    }

    @Test
    @WithAnonymousUser
    void test_home_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_addForm_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_validate_shouldReturnUnauthorized() throws Exception {
        CurvePoint curvePoint = new CurvePoint(3, 3.0, 3.0);
        mockMvc.perform(post("/curvePoint/validate").flashAttr("curvePoint",curvePoint))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_showUpdateForm_shouldReturnUnauthorized() throws Exception {
        CurvePoint curvePoint = new CurvePoint(3, 3.0, 3.0);
        when(curvePointService.findById(anyInt())).thenReturn(curvePoint);
        mockMvc.perform(get("/curvePoint/update/1").flashAttr("curvePoint",curvePoint))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_update_shouldReturnUnauthorized() throws Exception {
        CurvePoint curvePoint = new CurvePoint(3, 3.0, 3.0);
        when(curvePointService.findById(anyInt())).thenReturn(curvePoint);
        mockMvc.perform(post("/curvePoint/update/1").flashAttr("curvePoint",curvePoint))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_delete_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void test_home() throws Exception {
        when(curvePointService.findAll()).thenReturn(curvePointList);
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePointList"));
    }

    @Test
    @WithMockUser
    void test_addForm() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoint"));
    }

    @Test
    @WithMockUser
    void test_validate() throws Exception {
        CurvePoint curvePoint = new CurvePoint(3, 3.0, 3.0);
        mockMvc.perform(post("/curvePoint/validate").flashAttr("curvePoint",curvePoint))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void test_validate_withInvalidField() throws Exception {
        CurvePoint curvePoint = new CurvePoint(null, 3.0, 3.0);
        mockMvc.perform(post("/curvePoint/validate").flashAttr("curvePoint",curvePoint))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser
    void test_showUpdateForm() throws Exception {
        CurvePoint curvePoint = new CurvePoint(3, 3.0, 3.0);
        when(curvePointService.findById(anyInt())).thenReturn(curvePoint);
        mockMvc.perform(get("/curvePoint/update/1").flashAttr("curvePoint",curvePoint))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoint"));
    }

    @Test
    @WithMockUser
    void test_update() throws Exception {
        CurvePoint curvePoint = new CurvePoint(3, 3.0, 3.0);
        when(curvePointService.findById(anyInt())).thenReturn(curvePoint);
        mockMvc.perform(post("/curvePoint/update/1").flashAttr("curvePoint",curvePoint))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void test_update_withInvalidField() throws Exception {
        CurvePoint curvePoint = new CurvePoint(null, 3.0, 3.0);
        when(curvePointService.findById(anyInt())).thenReturn(curvePoint);
        mockMvc.perform(post("/curvePoint/update/1").flashAttr("curvePoint",curvePoint))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser
    void test_delete() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/1"))
                .andExpect(status().is3xxRedirection());
    }
}
