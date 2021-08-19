package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * The type Rule name.
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "json")
    private String template;

    @Column(name = "sql_str")
    private String sqlStr;

    @Column(name = "sql_part")
    private String sqlPart;
}
