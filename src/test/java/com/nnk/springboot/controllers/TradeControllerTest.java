package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.RuleNameService;
import com.nnk.springboot.services.TradeService;
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

@WebMvcTest(TradeController.class)
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    private static List<Trade> tradeList;

    @BeforeEach
    void setUpPerTest() {
        tradeList = new ArrayList<>();
        tradeList.add(new Trade("test 1","test 1",1.0));
        tradeList.add(new Trade("test 2","test 2",2.0));
    }

    @Test
    @WithAnonymousUser
    void test_home_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_addForm_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_validate_shouldReturnUnauthorized() throws Exception {
        Trade trade = new Trade("test","test",0.0);
        mockMvc.perform(post("/trade/validate").flashAttr("trade",trade))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_showUpdateForm_shouldReturnUnauthorized() throws Exception {
        Trade trade = new Trade("test","test",0.0);
        when(tradeService.findById(anyInt())).thenReturn(trade);
        mockMvc.perform(get("/trade/update/1").flashAttr("trade",trade))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_update_shouldReturnUnauthorized() throws Exception {
        Trade trade = new Trade("test","test",0.0);
        when(tradeService.findById(anyInt())).thenReturn(trade);
        mockMvc.perform(post("/trade/update/1").flashAttr("trade",trade))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_delete_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/trade/delete/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void test_home() throws Exception {
        when(tradeService.findAll()).thenReturn(tradeList);
        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("tradeList"));
    }

    @Test
    @WithMockUser
    void test_addForm() throws Exception {
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("trade"));
    }

    @Test
    @WithMockUser
    void test_validate() throws Exception {
        Trade trade = new Trade("test","test",0.0);
        mockMvc.perform(post("/trade/validate").flashAttr("trade",trade))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void test_validate_withInvalidField() throws Exception {
        Trade trade = new Trade(null,"test",0.0);
        mockMvc.perform(post("/trade/validate").flashAttr("trade",trade))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser
    void test_showUpdateForm() throws Exception {
        Trade trade = new Trade("test","test",0.0);
        when(tradeService.findById(anyInt())).thenReturn(trade);
        mockMvc.perform(get("/trade/update/1").flashAttr("trade",trade))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("trade"));
    }

    @Test
    @WithMockUser
    void test_update() throws Exception {
        Trade trade = new Trade("test","test",0.0);
        when(tradeService.findById(anyInt())).thenReturn(trade);
        mockMvc.perform(post("/trade/update/1").flashAttr("trade",trade))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void test_update_withInvalidField() throws Exception {
        Trade trade = new Trade(null,"test",0.0);
        when(tradeService.findById(anyInt())).thenReturn(trade);
        mockMvc.perform(post("/trade/update/1").flashAttr("trade",trade))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser
    void test_delete() throws Exception {
        mockMvc.perform(get("/trade/delete/1"))
                .andExpect(status().is3xxRedirection());
    }
}
