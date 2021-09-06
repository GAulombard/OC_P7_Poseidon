package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exception.AlreadyExistsException;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RuleNameServiceTest {

    @InjectMocks
    private RuleNameService ruleNameService;

    @Mock
    private RuleNameRepository ruleNameRepository;

    private static List<RuleName> ruleNameList;

    @BeforeEach
    void setUpPerTest() {
        ruleNameList = new ArrayList<>();
        ruleNameList.add(new RuleName("Test 1","Test 1","Test 1","Test 1","Test 1","Test 1"));
        ruleNameList.add(new RuleName("Test 2","Test 2","Test 2","Test 2","Test 2","Test 2"));
    }

    @Test
    void test_findAll() {
        when(ruleNameRepository.findAll()).thenReturn(ruleNameList);
        ruleNameService.findAll();
        verify(ruleNameRepository).findAll();
    }

    @Test
    void test_save() throws AlreadyExistsException {
        RuleName ruleName = new RuleName("Test","Test","Test","Test","Test","Test");
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
        ruleNameService.save(ruleName);
        verify(ruleNameRepository).save(ruleName);
    }

    @Test
    void test_findById() throws NotFoundException {
        RuleName ruleName = new RuleName("Test","Test","Test","Test","Test","Test");
        when(ruleNameRepository.existsById(anyInt())).thenReturn(true);
        when(ruleNameRepository.getOne(anyInt())).thenReturn(ruleName);
        ruleNameService.findById(anyInt());
        verify(ruleNameRepository).getOne(anyInt());
    }

    @Test
    void test_findById_shouldThrowNotFoundException() throws NotFoundException {
        RuleName ruleName = new RuleName("Test","Test","Test","Test","Test","Test");
        when(ruleNameRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(NotFoundException.class,() -> ruleNameService.findById(anyInt()));
    }

    @Test
    void test_deleteById() throws NotFoundException {
        when(ruleNameRepository.existsById(1)).thenReturn(true);
        doNothing().when(ruleNameRepository).deleteById(1);
        ruleNameService.deleteById(1);
        verify(ruleNameRepository).deleteById(1);
    }

    @Test
    void test_deleteById_shouldThrowsNotFoundException() throws NotFoundException {
        when(ruleNameRepository.existsById(1)).thenReturn(false);
        assertThrows(NotFoundException.class,() -> ruleNameService.deleteById(1));
    }
}
