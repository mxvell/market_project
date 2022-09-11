package com.example.market_project.service.bids;

import com.example.market_project.dao.bids.BidDAO;
import com.example.market_project.entity.Bid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Data
@Service(value = "BidServiceImplBean")
public class BidServiceImpl implements BidService{
    @Autowired
    private BidDAO bidDAO;

    @Override
    public void save(Bid bid) {
        bidDAO.save(bid);
    }

    @Override
    public List<Bid> getAll() {
        return bidDAO.getAll();
    }

    @Override
    public Bid getBest() {
        return bidDAO.getBest();
    }

    @Override
    public Bid get(int price) {
        return bidDAO.get(price);
    }

    @Override
    public int getSizeByPrice(int price) {
        return bidDAO.getSizeByPrice(price);
    }

    @Override
    public void delete(int price) {
    bidDAO.delete(price);
    }
}
