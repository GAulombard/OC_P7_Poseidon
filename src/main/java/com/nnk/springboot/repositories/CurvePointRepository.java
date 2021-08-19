package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The interface Curve point repository.
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
