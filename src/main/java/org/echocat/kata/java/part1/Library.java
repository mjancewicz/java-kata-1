package org.echocat.kata.java.part1;

import lombok.AllArgsConstructor;
import org.echocat.kata.java.part1.model.Publication;
import org.echocat.kata.java.part1.repository.PublicationRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class Library {

    private PublicationRepository publicationRepository;

    public Optional<Publication> findByIsbn(String isbn) {
        return publicationRepository.findByIsbn(isbn);
    }

    public List<Publication> findAll() {
        return publicationRepository.findAll();
    }

    public List<Publication> findByOrderByTitle() {
        return publicationRepository.findAll().stream()
                .sorted(Comparator.comparing(Publication::getTitle))
                .collect(toList());
    }

    public List<Publication> findByAuthor(String email) {
        return publicationRepository.findByAuthor(email);
    }
}
