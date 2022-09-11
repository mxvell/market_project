package com.example.market_project.fileManager;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component(value = "MyFileWriterBean")
@Data
public class MyFileWriter {
    private static final String fileName = "output.txt";
    private static File file;

    @PostConstruct
    public void init() {
        this.file = new File(fileName);
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStringToFile(String str) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.append(str);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
