package com.example.market_project;

import com.example.market_project.fileManager.FileManager;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;

@Data
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan(value = "com")
public class Config {
    @Bean
    public FileManager fileManagerBean(){
        return new FileManager(new File("input.txt"));
    }
}
