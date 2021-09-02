package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {

    private static Logger LOGGER = LoggerFactory.getLogger(RuleNameService.class);

    @Autowired
    private RuleNameRepository ruleNameRepository;

    public List<RuleName> findAll() {
        LOGGER.info("Process to find all rule name");

        return ruleNameRepository.findAll();
    }

    public void save(RuleName ruleName) {
        LOGGER.info("Process to save new rule name");

        ruleNameRepository.save(ruleName);
    }

    public RuleName findById(Integer id) throws NotFoundException {
        LOGGER.info("Process to find rule name by id");

        if(!ruleNameRepository.existsById(id)) throw new NotFoundException("rule name not found");

        return ruleNameRepository.getOne(id);
    }

    public void deleteById(Integer id) throws NotFoundException {
        LOGGER.info("Process to delete rule name by id");

        if(!ruleNameRepository.existsById(id)) throw new NotFoundException("rule name not found");

        ruleNameRepository.deleteById(id);;

    }
}
