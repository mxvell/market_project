package com.example.market_project.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bids")
@Data
public class Bid {
    @Id
    @Column(name = "price")
    private int price;

    @Column(name = "size")
    private int size;

    public Bid() {
    }

    public Bid(int price, int size) {
        this.price = price;
        this.size = size;
    }
}
