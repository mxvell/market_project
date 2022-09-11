package com.example.market_project.service.bids;

import com.example.market_project.entity.Bid;

import java.util.List;

public interface BidService {
    void save(Bid bid);
    List<Bid> getAll();
    Bid getBest();
    Bid get(int price);
    int getSizeByPrice(int price);
    void delete(int price);
}
