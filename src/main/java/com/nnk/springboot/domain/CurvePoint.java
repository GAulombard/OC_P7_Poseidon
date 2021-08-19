package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * The type Curve point.
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "curve_point")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "curve_id")
    private Integer curveId;

    @Column(name = "as_of_date")
    private LocalDateTime asOfDate;

    @Column(name = "term")
    private double term;

    @Column(name = "value")
    private double value;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

}
