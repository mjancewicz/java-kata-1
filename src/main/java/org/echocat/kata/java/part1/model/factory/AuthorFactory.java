package org.echocat.kata.java.part1.model.factory;

import org.echocat.kata.java.part1.model.Author;

import java.util.Map;

public class AuthorFactory {
    public Author create(Map<String, String> rowCells) {
        Author author = new Author();
        author.setEmail(rowCells.get("email"));
        author.setFirstname(rowCells.get("firstname"));
        author.setLastname(rowCells.get("lastname"));

        return author;
    }
}
