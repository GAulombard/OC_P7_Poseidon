package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.AlreadyExistsException;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
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
public class RatingServiceTest {
    @InjectMocks
    private RatingService ratingService;

    @Mock
    private RatingRepository ratingRepository;

    private static List<Rating> ratingList;

    @BeforeEach
    void setUpPerTest() {
        ratingList = new ArrayList<>();
        ratingList.add(new Rating("Test 1", "Test 1", "Test 1",1));
        ratingList.add(new Rating("Test 2", "Test 2", "Test 2",2));
    }

    @Test
    void test_findAll() {
        when(ratingRepository.findAll()).thenReturn(ratingList);
        ratingService.findAll();
        verify(ratingRepository).findAll();
    }

    @Test
    void test_save() {
        Rating rating = new Rating("Test", "Test", "Test",0);
        when(ratingRepository.save(rating)).thenReturn(rating);
        ratingService.save(rating);
        verify(ratingRepository).save(rating);
    }

    @Test
    void test_findById() throws NotFoundException {
        Rating rating = new Rating("Test", "Test", "Test",0);
        when(ratingRepository.existsById(anyInt())).thenReturn(true);
        when(ratingRepository.getOne(anyInt())).thenReturn(rating);
        ratingService.findById(anyInt());
        verify(ratingRepository).getOne(anyInt());
    }

    @Test
    void test_findById_shouldThrowNotFoundException() throws NotFoundException {
        Rating rating = new Rating("Test", "Test", "Test",0);
        when(ratingRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(NotFoundException.class,() -> ratingService.findById(anyInt()));
    }

    @Test
    void test_deleteById() throws NotFoundException {
        when(ratingRepository.existsById(1)).thenReturn(true);
        doNothing().when(ratingRepository).deleteById(1);
        ratingService.deleteById(1);
        verify(ratingRepository).deleteById(1);
    }

    @Test
    void test_deleteById_shouldThrowsNotFoundException() throws NotFoundException {
        when(ratingRepository.existsById(1)).thenReturn(false);
        assertThrows(NotFoundException.class,() -> ratingService.deleteById(1));
    }
}
