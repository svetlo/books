package com.example.books;

import com.example.books.entity.Book;
import com.example.books.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void parseCsvTest() throws IOException {
        InputStream is = resourceLoader.getResource("classpath:books.csv").getInputStream();
        List<Book> books = bookService.fromCsv(is);
        //noinspection unchecked
        assertThat(books)
                .isNotNull()
                .hasSize(2)
                .extracting(Book::getAuthor, Book::getName)
                .contains(tuple("Mama", "Stories"), tuple("Alena", "Sveta i Medved"));
    }
}
