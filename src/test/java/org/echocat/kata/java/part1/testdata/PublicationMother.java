package org.echocat.kata.java.part1.testdata;

import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazine;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PublicationMother {
    public static Magazine mockMagazine(String title) {
        Magazine magazine = new Magazine();
        magazine.setTitle(title);
        magazine.setIsbn("magazineIsbn");
        magazine.setAuthors(newArrayList("author2"));

        return magazine;
    }

    public static Book mockBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn("bookIsbn");
        book.setAuthors(newArrayList("author1", "author2"));
        
        return book;
    }
}
