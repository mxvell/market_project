package com.example.market_project.dao.bids;

import com.example.market_project.entity.Bid;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface BidDAO {
    void save(Bid bid);
    List<Bid> getAll();
    Bid getBest();
    Bid get(int price);
    int getSizeByPrice(int price);
    void delete(int price);
}
