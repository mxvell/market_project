package com.example.market_project;

import com.example.market_project.fileManager.FileManager;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MarketProjectApplication {
    @SneakyThrows
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MarketProjectApplication.class, args);
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        FileManager fileManager = context.getBean("fileManagerBean", FileManager.class);
        fileManager.process();

    }

}
