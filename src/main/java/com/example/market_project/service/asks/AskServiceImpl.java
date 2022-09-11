package com.example.market_project.service.asks;

import com.example.market_project.dao.asks.AskDAO;
import com.example.market_project.entity.Ask;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service(value = "AskServiceImplBean")
public class AskServiceImpl implements AskService {
    @Qualifier("AskDAOImplBean")
    private AskDAO askDAO;

    @Override
    public void save(Ask ask) {
        askDAO.save(ask);
    }

    @Override
    public List<Ask> getAll() {
        return askDAO.getAll();
    }

    @Override
    public Ask getBest() {
        return askDAO.getBest();
    }

    @Override
    public Ask get(int price) {
        return askDAO.get(price);
    }

    @Override
    public int getSizeByPrice(int price) {
        return askDAO.getSizeByPrice(price);
    }

    @Override
    public void delete(int price) {
        askDAO.delete(price);
    }
}
