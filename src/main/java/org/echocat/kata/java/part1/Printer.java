package org.echocat.kata.java.part1;

import lombok.AllArgsConstructor;
import org.echocat.kata.java.part1.model.Author;
import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazine;
import org.echocat.kata.java.part1.model.Publication;
import org.echocat.kata.java.part1.repository.AuthorRepository;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

@AllArgsConstructor
public class Printer {

    private AuthorRepository authorRepository;

    public String print(List<Publication> publications) {
        return publications
                .stream().map(this::print)
                .collect(joining("\n"));
    }

    public String print(Publication publication) {
        StringBuilder sb = new StringBuilder();
        sb.append(format("Title: %s\n", publication.getTitle()));
        sb.append(String.format("ISBN: %s\n", publication.getIsbn()));
        if (publication instanceof Book) {
            sb.append(String.format("Description: %s\n", ((Book) publication).getDescription()));
        } else if (publication instanceof Magazine) {
            sb.append(String.format("Published at: %s\n", ((Magazine) publication).getPublishedAt()));
        }

        sb.append("Authors: \n");
        sb.append(publication.getAuthors().stream()
                .map(email -> {
                    Author author = authorRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
                    return String.format("\t- %s %s (%s)", author.getFirstname(), author.getLastname(), author.getEmail());
                }).collect(joining("\n")));

        return sb.toString();
    }
}
