package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Rule name repository.
 */
@Repository
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
