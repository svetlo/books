package com.example.books.service;

import com.example.books.entity.Book;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BookService {

    private final Logger logger;

    @Autowired
    public BookService(Logger logger) {
        this.logger = logger;
    }

    public List<Book> fromCsv(InputStream inputStream) throws IOException {
        CsvSchema schema = CsvSchema.builder()
                .addColumn("author")
                .addColumn("name")
                .build();
        MappingIterator<Book> it = new CsvMapper().readerFor(Book.class).with(schema).readValues(inputStream);
        return it.readAll();
    }

}
