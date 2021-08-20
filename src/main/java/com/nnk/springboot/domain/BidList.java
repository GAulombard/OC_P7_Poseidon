package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "bid_list")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_list_id")
    private Integer bidListId;

    @Column(name = "account")
    @NotEmpty(message = "Account is mandatory")
    private String account;

    @Column(name = "type")
    @NotEmpty(message = "Type is mandatory")
    private String type;

    @Column(name = "bid_quantity")
    @NotNull(message = "Bid quantity is mandatory")
    private double bidQuantity;

    @Column(name = "ask_quantity")
    @NotNull(message = "Ask quantity is mandatory")
    private double askQuantity;

    @Column(name = "bid")
    @NotNull(message = "Bid is mandatory")
    private double bid;

    @Column(name = "ask")
    @NotNull(message = "Ask is mandatory")
    private double ask;

    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "bid_list_date")
    private LocalDateTime bidListDate;

    @Column(name = "commentary")
    private String commentary;

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

    public BidList() {

    }

    public BidList(String account, String type, double bidQuantity) {

        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;

    }

}
