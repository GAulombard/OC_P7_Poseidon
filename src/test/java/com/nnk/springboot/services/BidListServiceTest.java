package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
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
public class BidListServiceTest {

    @InjectMocks
    private BidListService bidListService;

    @Mock
    private BidListRepository bidListRepository;

    private static List<BidList> bidListList;

    @BeforeEach
    void setUpPerTest() {
        bidListList = new ArrayList<>();
        bidListList.add(new BidList("Test account", "Test type", 10.00d));
        bidListList.add(new BidList("Test account2", "Test type2", 20.00d));
    }

    @Test
    void test_findAll() {
        when(bidListRepository.findAll()).thenReturn(bidListList);
        bidListService.findAll();
        verify(bidListRepository).findAll();
    }

    @Test
    void test_save() {
        BidList bidList = new BidList("Test account", "Test type", 10.00d);
        when(bidListRepository.save(bidList)).thenReturn(bidList);
        bidListService.save(bidList);
        verify(bidListRepository).save(bidList);
    }

    @Test
    void test_findBidById() throws NotFoundException {
        BidList bidList = new BidList("Test account", "Test type", 10.00d);
        when(bidListRepository.existsById(anyInt())).thenReturn(true);
        when(bidListRepository.getOne(1)).thenReturn(bidList);
        bidListService.findBidById(1);
        verify(bidListRepository).getOne(1);
    }

    @Test
    void test_findBidById_shouldThrowNotFoundException() throws NotFoundException {
        BidList bidList = new BidList("Test account", "Test type", 10.00d);
        when(bidListRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(NotFoundException.class,() -> bidListService.findBidById(anyInt()));
    }

    @Test
    void test_deleteBidById() throws NotFoundException {
        doNothing().when(bidListRepository).deleteById(1);
        bidListService.deleteBidById(1);
        verify(bidListRepository).deleteById(1);
    }
}
