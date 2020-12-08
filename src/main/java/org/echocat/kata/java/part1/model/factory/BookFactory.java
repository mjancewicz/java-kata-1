package org.echocat.kata.java.part1.model.factory;

import org.echocat.kata.java.part1.model.Book;

import java.util.Map;

import static com.google.common.base.Splitter.on;

public class BookFactory {
    public Book create(Map<String, String> rowCells) {
        Book book = new Book();
        book.setTitle(rowCells.get("title"));
        book.setIsbn(rowCells.get("isbn"));
        book.setAuthors(on(",").splitToList(rowCells.get("authors")));
        book.setDescription(rowCells.get("description"));

        return book;
    }
}
