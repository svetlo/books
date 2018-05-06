package com.example.books;


import com.example.books.entity.Book;
import com.example.books.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    @Test
    public void initDbTest() {
        List<Book> books = repository.findAll();
        assertThat(books).hasSize(2);
    }
}
