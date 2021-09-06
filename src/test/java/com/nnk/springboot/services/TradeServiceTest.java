package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exception.AlreadyExistsException;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeServiceTest {

    @InjectMocks
    private TradeService tradeService;

    @Mock
    private TradeRepository tradeRepository;

    private static List<Trade> tradeList;

    @BeforeEach
    void setUpPerTest() {
        tradeList = new ArrayList<>();
        tradeList.add(new Trade("Test 1","Test 1",1));
        tradeList.add(new Trade("Test 2","Test 2",2));
    }

    @Test
    void test_findAll() {
        when(tradeRepository.findAll()).thenReturn(tradeList);
        tradeService.findAll();
        verify(tradeRepository).findAll();
    }

    @Test
    void test_save() throws AlreadyExistsException {
        Trade trade = new Trade("Test","Test",0);
        when(tradeRepository.save(trade)).thenReturn(trade);
        tradeService.save(trade);
        verify(tradeRepository).save(trade);
    }

    @Test
    void test_findById() throws NotFoundException {
        Trade trade = new Trade("Test","Test",0);
        when(tradeRepository.existsById(anyInt())).thenReturn(true);
        when(tradeRepository.getOne(anyInt())).thenReturn(trade);
        tradeService.findById(anyInt());
        verify(tradeRepository).getOne(anyInt());
    }

    @Test
    void test_findById_shouldThrowNotFoundException() throws NotFoundException {
        Trade trade = new Trade("Test","Test",0);
        when(tradeRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(NotFoundException.class,() -> tradeService.findById(anyInt()));
    }

    @Test
    void test_deleteById() throws NotFoundException {
        when(tradeRepository.existsById(1)).thenReturn(true);
        doNothing().when(tradeRepository).deleteById(1);
        tradeService.deleteById(1);
        verify(tradeRepository).deleteById(1);
    }

    @Test
    void test_deleteById_shouldThrowsNotFoundException() throws NotFoundException {
        when(tradeRepository.existsById(1)).thenReturn(false);
        assertThrows(NotFoundException.class,() -> tradeService.deleteById(1));
    }
}
