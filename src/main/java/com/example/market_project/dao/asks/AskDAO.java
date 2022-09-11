package com.example.market_project.dao.asks;

import com.example.market_project.entity.Ask;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface AskDAO {
    void save(Ask ask);
    List<Ask> getAll();
    Ask getBest();
    Ask get(int price);
    int getSizeByPrice(int price);
    void delete(int price);

}
