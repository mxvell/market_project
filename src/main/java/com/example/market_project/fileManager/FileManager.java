package com.example.market_project.fileManager;

import com.example.market_project.dao.asks.AskDAO;
import com.example.market_project.entity.Ask;
import com.example.market_project.entity.Bid;
import com.example.market_project.service.bids.BidService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@Data
public class FileManager {
    private File file;

    @Autowired
    private AskDAO askService;

    @Autowired
    private BidService bidService;

    public FileManager() {
    }

    public FileManager(File file) {
        this.file = file;
    }

    public void process() {
        System.out.println((askService == null) + ", " + (bidService == null));
        ArrayList<String> linesList = getFileLines();
        for (int i = 0; i < linesList.size(); i++) {
            String lineOperator = linesList.get(i).split(",")[0].toLowerCase();
            if (lineOperator.equals("u")) {
                this.doUpdate(linesList.get(i));
            } else if (lineOperator.equals("q")) {
                this.doQuery(linesList.get(i));
            } else if (lineOperator.equals("o")) {
                this.doOrder(linesList.get(i));
            } else {
                System.out.println("'u', 'q' or 'o' expected, but got '" + lineOperator + "'");
            }
        }
    }

    private ArrayList<String> getFileLines() {

        return new ArrayList(Arrays.asList(MyFileReader.getText(file).split(";")));
    }

    private void doUpdate(String line) {
        String[] parts = line.split(",");
        int price = Integer.parseInt(parts[1]);
        int size = Integer.parseInt(parts[2]);
        String type = parts[3].toLowerCase();

        if (type.equals("bid")) {
            bidService.save(new Bid(price, size));
        } else if (type.equals("ask")) {
            askService.save(new Ask(price, size));
        } else {
            System.out.println("'bid' or 'ask' expected, but got '" + type + "'");
        }

    }

    private void doQuery(String line) {
        String[] parts = line.split(",");
        if (parts[1].equals("size")) {
            int size;
            int value = Integer.parseInt(parts[2]);
            if (bidService.getSizeByPrice(value) != 0) {
                size = bidService.getSizeByPrice(value);
            } else if (askService.getSizeByPrice(value) != 0) {
                size = askService.getSizeByPrice(value);
            } else {
                size = 0;
            }
            MyFileWriter.writeStringToFile("" + size);

        } else if (parts[1].equals("best_bid")) {
            int price = bidService.getBest().getPrice();
            int size = bidService.getBest().getSize();
            MyFileWriter.writeStringToFile(price + "," + size);

        } else if (parts[1].equals("best_ask")) {
            int price = askService.getBest().getPrice();
            int size = askService.getBest().getSize();
            MyFileWriter.writeStringToFile(price + "," + size);
        } else {
            System.out.println("'size', 'best_bid' or 'best_ask expected', but got '" + parts[1] + "'");
        }
    }

    private void doOrder(String line) {
        String[] parts = line.split(",");
        if (parts[1].equals("buy")) {
            Ask ask = askService.getBest();
            ask.setSize(ask.getSize() - Integer.parseInt(parts[2]));
            askService.save(ask);
        } else if (parts[1].equals("sell")) {
            Bid bid = bidService.getBest();
            bid.setSize(bid.getSize() - Integer.parseInt(parts[2]));
            bidService.save(bid);
        } else {
            System.out.println("'buy' or 'sell' expected, but got '" + parts[1] + "'");
        }
    }
}
