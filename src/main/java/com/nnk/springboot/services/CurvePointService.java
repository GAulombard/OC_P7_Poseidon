package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exception.AlreadyExistsException;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Curve point service.
 */
@Service
public class CurvePointService {

    private static Logger LOGGER = LoggerFactory.getLogger(CurvePointService.class);

    @Autowired
    private CurvePointRepository curvePointRepository;

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<CurvePoint> findAll() {
        LOGGER.info("Process to find all curve point");

        return curvePointRepository.findAll();
    }

    /**
     * Save.
     *
     * @param curvePoint the curve point
     * @throws AlreadyExistsException the already exists exception
     */
    public void save(CurvePoint curvePoint) throws AlreadyExistsException {
        LOGGER.info("Process to save new curve point");

        //if(curvePointRepository.existsById(curvePoint.getId())) throw new AlreadyExistsException("Curve point already exists.");

        curvePointRepository.save(curvePoint);
    }

    /**
     * Find by id curve point.
     *
     * @param id the id
     * @return the curve point
     * @throws NotFoundException the not found exception
     */
    public CurvePoint findById(Integer id) throws NotFoundException {
        LOGGER.info("Process to find curve point by id");

        if(!curvePointRepository.existsById(id)) throw new NotFoundException("Curve point not found");

        return curvePointRepository.getOne(id);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     * @throws NotFoundException the not found exception
     */
    public void deleteById(Integer id) throws NotFoundException {
        LOGGER.info("Process to delete curve point by id");

        if(!curvePointRepository.existsById(id)) throw new NotFoundException("Curve point not found");

        curvePointRepository.deleteById(id);

    }
}
