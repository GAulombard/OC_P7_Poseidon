package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Rule name service.
 */
@Service
public class RuleNameService {

    private static Logger LOGGER = LoggerFactory.getLogger(RuleNameService.class);

    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<RuleName> findAll() {
        LOGGER.info("Process to find all rule name");

        return ruleNameRepository.findAll();
    }

    /**
     * Save.
     *
     * @param ruleName the rule name
     */
    public void save(RuleName ruleName) {
        LOGGER.info("Process to save new rule name");

        ruleNameRepository.save(ruleName);
    }

    /**
     * Find by id rule name.
     *
     * @param id the id
     * @return the rule name
     * @throws NotFoundException the not found exception
     */
    public RuleName findById(Integer id) throws NotFoundException {
        LOGGER.info("Process to find rule name by id");

        if(!ruleNameRepository.existsById(id)) throw new NotFoundException("rule name not found");

        return ruleNameRepository.getOne(id);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     * @throws NotFoundException the not found exception
     */
    public void deleteById(Integer id) throws NotFoundException {
        LOGGER.info("Process to delete rule name by id");

        if(!ruleNameRepository.existsById(id)) throw new NotFoundException("rule name not found");

        ruleNameRepository.deleteById(id);;

    }
}
