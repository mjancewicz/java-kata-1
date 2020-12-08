package org.echocat.kata.java.part1.repository;

import org.echocat.kata.java.part1.model.Publication;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

public class PublicationRepository {
    private BookRepository bookRepository;
    private MagazineRepository magazineRepository;

    public PublicationRepository(BookRepository bookRepository, MagazineRepository magazineRepository) {
        this.bookRepository = bookRepository;
        this.magazineRepository = magazineRepository;
    }

    public List<Publication> findAll() {
        List<Publication> rows = newArrayList();
        rows.addAll(bookRepository.findAll());
        rows.addAll(magazineRepository.findAll());

        return rows;
    }

    public List<Publication> findByAuthor(String email) {
        return findAll().stream().filter(p -> p.getAuthors().contains(email)).collect(toList());
    }

    public Optional<Publication> findByIsbn(String isbn) {
        return findAll().stream().filter(p -> p.getIsbn().equals(isbn)).findFirst();
    }
}
