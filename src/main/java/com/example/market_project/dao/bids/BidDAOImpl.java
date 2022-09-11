package com.example.market_project.dao.bids;

import com.example.market_project.entity.Bid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
@Data
@Component
public class BidDAOImpl implements BidDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(Bid bid) {
        if (this.get(bid.getPrice()) == null) {
            entityManager.persist(bid);
        } else {
            entityManager.merge(bid);
        }
    }

    @Override
    public List<Bid> getAll() {
        Query query = entityManager.createQuery("from  Bid");
        List<Bid> result = query.getResultList();
        return result;
    }

    @Override
    public Bid getBest() {
        Query query = entityManager.createNativeQuery("SELECT price, size from bids where price  =  (SELECT max (price) from bids)",
                Bid.class);
        return (Bid) query.getResultList().get(0);
    }

    @Override
    public Bid get(int price) {
        Bid result = entityManager.find(Bid.class, price);
        return result;
    }

    @Override
    public int getSizeByPrice(int price) {
        Bid bid = entityManager.find(Bid.class, price);
        return bid.getSize();
    }

    @Override
    public void delete(int price) {
        Query query = entityManager.createQuery("delete from bid where  price=:price");
        query.setParameter("price", price);
        query.executeUpdate();
    }
}
