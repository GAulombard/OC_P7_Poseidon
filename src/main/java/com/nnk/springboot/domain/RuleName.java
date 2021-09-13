package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

/**
 * The type Rule name.
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "rule_name")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Description is mandatory")
    @Column(name = "description")
    private String description;

    @NotEmpty(message = "Json is mandatory")
    @Column(name = "json")
    private String json;

    @NotEmpty(message = "Template is mandatory")
    @Column(name = "template")
    private String template;

    @NotEmpty(message = "SqlStr is mandatory")
    @Column(name = "sql_str")
    private String sqlStr;

    @NotEmpty(message = "SqlPart is mandatory")
    @Column(name = "sql_part")
    private String sqlPart;

    /**
     * Instantiates a new Rule name.
     *
     * @param name        the name
     * @param description the description
     * @param json        the json
     * @param template    the template
     * @param sqlStr      the sql str
     * @param sqlPart     the sql part
     */
    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    /**
     * Instantiates a new Rule name.
     */
    public RuleName() {

    }
}
