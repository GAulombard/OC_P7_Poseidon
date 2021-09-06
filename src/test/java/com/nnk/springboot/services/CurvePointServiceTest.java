package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exception.AlreadyExistsException;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
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
public class CurvePointServiceTest {

    @InjectMocks
    private CurvePointService curvePointService;

    @Mock
    private CurvePointRepository curvePointRepository;

    private static List<CurvePoint> curvePoint;

    @BeforeEach
    void setUpPerTest() {
        curvePoint = new ArrayList<>();
        curvePoint.add(new CurvePoint(1, 2, 3));
        curvePoint.add(new CurvePoint(4, 5, 6));
    }

    @Test
    void test_findAll() {
        when(curvePointRepository.findAll()).thenReturn(curvePoint);
        curvePointService.findAll();
        verify(curvePointRepository).findAll();
    }

    @Test
    void test_save() throws AlreadyExistsException {
        CurvePoint curvePoint1 = new CurvePoint(7,8,9);
        when(curvePointRepository.save(curvePoint1)).thenReturn(curvePoint1);
        curvePointService.save(curvePoint1);
        verify(curvePointRepository).save(curvePoint1);
    }

    @Test
    void test_findById() throws NotFoundException {
        CurvePoint curvePoint1 = new CurvePoint(7,8,9);
        when(curvePointRepository.existsById(anyInt())).thenReturn(true);
        when(curvePointRepository.getOne(anyInt())).thenReturn(curvePoint1);
        curvePointService.findById(anyInt());
        verify(curvePointRepository).getOne(anyInt());
    }

    @Test
    void test_findById_shouldThrowNotFoundException() throws NotFoundException {
        CurvePoint curvePoint1 = new CurvePoint(7,8,9);
        when(curvePointRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(NotFoundException.class,() -> curvePointService.findById(anyInt()));
    }

    @Test
    void test_deleteById() throws NotFoundException {
        when(curvePointRepository.existsById(1)).thenReturn(true);
        doNothing().when(curvePointRepository).deleteById(1);
        curvePointService.deleteById(1);
        verify(curvePointRepository).deleteById(1);
    }

    @Test
    void test_deleteById_shouldThrowsNotFoundException() throws NotFoundException {
        when(curvePointRepository.existsById(1)).thenReturn(false);
        assertThrows(NotFoundException.class,() -> curvePointService.deleteById(1));
    }
}
