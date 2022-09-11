package com.example.market_project.fileManager;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Data
public class MyFileReader {
    public static String getText(File file) {
        StringBuilder sb = new StringBuilder();
        long after = System.currentTimeMillis();
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                String line = new String();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    sb.append(";");
                }
                sb.deleteCharAt(sb.length() - 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long before = System.currentTimeMillis();
        System.out.println("Time: " + (before - after));
        return sb.toString();
    }
}
