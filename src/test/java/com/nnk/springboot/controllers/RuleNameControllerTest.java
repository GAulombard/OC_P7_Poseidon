package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.RuleNameService;
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

@WebMvcTest(RuleNameController.class)
public class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameService ruleNameService;

    private static List<RuleName> ruleNameList;

    @BeforeEach
    void setUpPerTest() {
        ruleNameList = new ArrayList<>();
        ruleNameList.add(new RuleName("test 1","test 1","test 1", "test 1","test 1","test 1"));
        ruleNameList.add(new RuleName("test 2","test 2","test 2", "test 2","test 2","test 2"));
    }

    @Test
    @WithAnonymousUser
    void test_home_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_addForm_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_validate_shouldReturnUnauthorized() throws Exception {
        RuleName ruleName = new RuleName("test","test","test", "test","test","test");
        mockMvc.perform(post("/ruleName/validate").flashAttr("ruleName",ruleName))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_showUpdateForm_shouldReturnUnauthorized() throws Exception {
        RuleName ruleName = new RuleName("test","test","test", "test","test","test");
        when(ruleNameService.findById(anyInt())).thenReturn(ruleName);
        mockMvc.perform(get("/ruleName/update/1").flashAttr("ruleName",ruleName))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_update_shouldReturnUnauthorized() throws Exception {
        RuleName ruleName = new RuleName("test","test","test", "test","test","test");
        when(ruleNameService.findById(anyInt())).thenReturn(ruleName);
        mockMvc.perform(post("/ruleName/update/1").flashAttr("ruleName",ruleName))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithAnonymousUser
    void test_delete_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/ruleName/delete/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void test_home() throws Exception {
        when(ruleNameService.findAll()).thenReturn(ruleNameList);
        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleNameList"));
    }

    @Test
    @WithMockUser
    void test_addForm() throws Exception {
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleName"));
    }

    @Test
    @WithMockUser
    void test_validate() throws Exception {
        RuleName ruleName = new RuleName("test","test","test", "test","test","test");
        mockMvc.perform(post("/ruleName/validate").flashAttr("ruleName",ruleName))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void test_validate_withInvalidField() throws Exception {
        RuleName ruleName = new RuleName(null,"test","test", "test","test","test");
        mockMvc.perform(post("/ruleName/validate").flashAttr("ruleName",ruleName))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser
    void test_showUpdateForm() throws Exception {
        RuleName ruleName = new RuleName("test","test","test", "test","test","test");
        when(ruleNameService.findById(anyInt())).thenReturn(ruleName);
        mockMvc.perform(get("/ruleName/update/1").flashAttr("ruleName",ruleName))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleName"));
    }

    @Test
    @WithMockUser
    void test_update() throws Exception {
        RuleName ruleName = new RuleName("test","test","test", "test","test","test");
        when(ruleNameService.findById(anyInt())).thenReturn(ruleName);
        mockMvc.perform(post("/ruleName/update/1").flashAttr("ruleName",ruleName))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void test_update_withInvalidField() throws Exception {
        RuleName ruleName = new RuleName(null,"test","test", "test","test","test");
        when(ruleNameService.findById(anyInt())).thenReturn(ruleName);
        mockMvc.perform(post("/ruleName/update/1").flashAttr("ruleName",ruleName))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser
    void test_delete() throws Exception {
        mockMvc.perform(get("/ruleName/delete/1"))
                .andExpect(status().is3xxRedirection());
    }
}
