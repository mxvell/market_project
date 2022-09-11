package com.example.market_project.service.asks;

import com.example.market_project.entity.Ask;

import java.util.List;

public interface AskService {
    void save(Ask ask);
    List<Ask> getAll();
    Ask getBest();
    Ask get(int price);
    int getSizeByPrice(int price);
    void delete(int price);
}
