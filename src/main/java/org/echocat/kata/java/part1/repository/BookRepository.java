package org.echocat.kata.java.part1.repository;

import lombok.AllArgsConstructor;
import org.echocat.kata.java.part1.model.Book;

import java.util.List;

@AllArgsConstructor
public class BookRepository {

    private List<Book> rows;

    public List<Book> findAll() {
        return rows;
    }
}