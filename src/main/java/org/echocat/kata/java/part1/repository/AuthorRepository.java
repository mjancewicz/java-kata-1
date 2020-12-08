package org.echocat.kata.java.part1.repository;

import lombok.AllArgsConstructor;
import org.echocat.kata.java.part1.model.Author;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class AuthorRepository {

    private List<Author> rows;

    public Optional<Author> findByEmail(String email) {
        return rows.stream().filter(row -> row.getEmail().equals(email)).findFirst();
    }
}
