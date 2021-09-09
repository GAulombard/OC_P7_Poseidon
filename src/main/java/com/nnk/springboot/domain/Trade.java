package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Integer tradeId;

    @NotEmpty(message = "Account is mandatory")
    @Column(name = "account")
    private String account;

    @NotEmpty(message = "Account is mandatory")
    @Column(name = "type")
    private String type;

    @NotNull(message = "Buy quantity is mandatory")
    @Min(value=0,message = "Buy quantity must be a positive number")
    @Digits(integer = 8, fraction = 2, message = "buyQuantity should be a number")
    @Column(name = "buy_quantity")
    private double buyQuantity;

    @Column(name = "sell_quantity")
    private double sellQuantity;

    @Column(name = "buy_price")
    private double buyPrice;

    @Column(name = "sell_price")
    private double sellPrice;

    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "trade_date")
    private LocalDateTime tradeDate;

    @Column(name = "security")
    private String security;

    @Column(name = "status")
    private String status;

    @Column(name = "trader")
    private String trader;

    @Column(name = "book")
    private String book;

    @Column(name = "creation_name")
    private String creationName;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "revision_name")
    private String revisionName;

    @Column(name = "revision_date")
    private LocalDateTime revisionDate;

    @Column(name = "deal_name")
    private String dealName;

    @Column(name = "deal_type")
    private String dealType;

    @Column(name = "source_list_id")
    private String sourceListId;

    @Column(name = "side")
    private String side;

    public Trade() {

    }

    public Trade(String account, String type, double buyQuantity) {

        this.buyQuantity = buyQuantity;
        this.account = account;
        this.type = type;

    }

}
