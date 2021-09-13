package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

/**
 * The type Rating.
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "Moodys rating is mandatory")
    @Column(name = "moodys_rating")
    private String moodysRating;

    @NotEmpty(message = "Sand P rating is mandatory")
    @Column(name = "sand_p_rating")
    private String sandPRating;

    @NotEmpty(message = "Fitch rating is mandatory")
    @Column(name = "fitch_rating")
    private String fitchRating;

    @NotNull(message = "Order number is mandatory")
    @Min(value = 0,message = "Order number must be a positive number")
    @Digits(integer = 8, fraction = 0, message = "orderNumber should be a integer number")
    @Column(name = "order_number")
    private Integer orderNumber;

    /**
     * Instantiates a new Rating.
     *
     * @param moodysRating the moodys rating
     * @param sandPRating  the sand p rating
     * @param fitchRating  the fitch rating
     * @param orderNumber  the order number
     */
    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    /**
     * Instantiates a new Rating.
     */
    public Rating() {

    }
}
