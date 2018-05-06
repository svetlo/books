package com.example.books.config;

import com.example.books.entity.Book;
import com.example.books.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DbConfig {

    @Bean
    public CommandLineRunner initDb(BookRepository repository) {
        return args -> {
            repository.saveAll(Arrays.asList(
                    new Book("Spring for Dummies", "Joshua Long"),
                    new Book("The Best Programmer", "Svetlana Nikitina"))
            );
        };
    }


}

