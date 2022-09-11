package com.example.market_project.dao.asks;

import com.example.market_project.entity.Ask;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository(value = "AskDAOImplBean")
@Data
@Component
public class AskDAOImpl implements AskDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(Ask ask) {
        if (this.get(ask.getPrice()) == null) {
            entityManager.persist(ask);
        } else {
            entityManager.merge(ask);
        }
    }

    @Override
    public List<Ask> getAll() {
        Query query = entityManager.createQuery("from  Ask");
        List<Ask> result = query.getResultList();
        return result;
    }

    @Override
    public Ask getBest() {
        Query query = entityManager.createNativeQuery("SELECT price, size from asks where price  =  (SELECT min (price) from asks)",
                Ask.class);
        Ask result = (Ask) query.getResultList().get(0);
        return result;
    }

    @Override
    public Ask get(int price) {
        Ask result = entityManager.find(Ask.class, price);
        return result;
    }

    @Override
    public int getSizeByPrice(int price) {
        Ask ask = entityManager.find(Ask.class, price);
        return ask.getPrice();
    }

    @Override
    public void delete(int price) {
        Query query = entityManager.createQuery("delete from ask where  price=:price");
        query.setParameter("price", price);
        query.executeUpdate();
    }
}
