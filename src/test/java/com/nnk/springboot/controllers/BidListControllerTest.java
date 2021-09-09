package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(BidListController.class)
public class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService bidListService;

    private static List<BidList> bidListList;

    @BeforeEach
    void setUpPerTest() {
        bidListList = new ArrayList<>();
        bidListList.add(new BidList("Test account", "Test type", 10.00d));
        bidListList.add(new BidList("Test account2", "Test type2", 20.00d));
    }

    @Test
    @WithAnonymousUser
    void test_home_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_addForm_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_validate_shouldReturnUnauthorized() throws Exception {
        BidList bidList = new BidList("test","test",1.0);
        mockMvc.perform(post("/bidList/validate").flashAttr("bidList",bidList))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_showUpdateForm_shouldReturnUnauthorized() throws Exception {
        BidList bidList = new BidList("test","test",1.0);
        when(bidListService.findBidById(anyInt())).thenReturn(bidList);
        mockMvc.perform(get("/bidList/update/1").flashAttr("bidList",bidList))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_update_shouldReturnUnauthorized() throws Exception {
        BidList bidList = new BidList("test","test",1.0);
        when(bidListService.findBidById(anyInt())).thenReturn(bidList);
        mockMvc.perform(post("/bidList/update/1").flashAttr("bidList",bidList))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_delete_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/bidList/delete/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void test_home() throws Exception {
        when(bidListService.findAll()).thenReturn(bidListList);
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"));
    }

    @Test
    @WithMockUser
    void test_addForm() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"));
    }

    @Test
    @WithMockUser
    void test_validate() throws Exception {
        BidList bidList = new BidList("test","test",1.0);
        mockMvc.perform(post("/bidList/validate").flashAttr("bidList",bidList))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void test_validate_withInvalidField() throws Exception {
        BidList bidList = new BidList(null,"test",1.0);
        mockMvc.perform(post("/bidList/validate").flashAttr("bidList",bidList))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser
    void test_showUpdateForm() throws Exception {
        BidList bidList = new BidList("test","test",1.0);
        when(bidListService.findBidById(anyInt())).thenReturn(bidList);
        mockMvc.perform(get("/bidList/update/1").flashAttr("bidList",bidList))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"));
    }

    @Test
    @WithMockUser
    void test_update() throws Exception {
        BidList bidList = new BidList("test","test",1.0);
        when(bidListService.findBidById(anyInt())).thenReturn(bidList);
        mockMvc.perform(post("/bidList/update/1").flashAttr("bidList",bidList))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void test_update_withInvalidField() throws Exception {
        BidList bidList = new BidList(null,"test",1.0);
        when(bidListService.findBidById(anyInt())).thenReturn(bidList);
        mockMvc.perform(post("/bidList/update/1").flashAttr("bidList",bidList))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser
    void test_delete() throws Exception {
        mockMvc.perform(get("/bidList/delete/1"))
                .andExpect(status().is3xxRedirection());
    }
}
