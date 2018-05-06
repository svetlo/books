package com.example.books.controller;

import com.example.books.entity.Book;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.*;

@Controller
public class BookController {

    private final BookRepository repository;
    private final BookService service;
    private final Logger logger;

    @Autowired
    public BookController(BookRepository repository, BookService service, Logger logger) {
        this.repository = repository;
        this.service = service;
        this.logger = logger;
    }

    @RequestMapping({"/books", "/"})
    public String books(Model model) {
        List<Book> books = repository.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            repository.saveAll(service.fromCsv(file.getInputStream()));
        } catch (IOException e) {
            logger.error("Exception while reading books:", e);
            throw new BookParseException(e.getMessage());
        }
        return "redirect:/books";
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public String upload(@PathVariable("id") Integer id) {
        Optional<Book> book = repository.findById(id);
        book.ifPresent(repository::delete);
        return "redirect:/books";
    }
//
//    @ExceptionHandler(BookParseException.class)
//    public RedirectView handleUploadException(BookParseException e, RedirectAttributes redirectAttributes) {
//        redirectAttributes.addFlashAttribute("error", e.getMessage());
//        return new RedirectView("/books");
//    }

//    @ExceptionHandler(BookParseException.class)
//    public @ResponseBody
//    Map<String, String> handleUploadException(BookParseException e) {
//        return Collections.singletonMap("error", e.getMessage());
//    }

}
