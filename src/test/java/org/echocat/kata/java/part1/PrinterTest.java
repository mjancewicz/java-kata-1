package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.model.Author;
import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazine;
import org.echocat.kata.java.part1.model.Publication;
import org.echocat.kata.java.part1.repository.AuthorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrinterTest {

    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private Printer printer;

    @Before
    public void before() {
        when(authorRepository.findByEmail(any())).thenReturn(of(createAuthor()));
    }

    private Author createAuthor() {
        Author author = new Author();
        author.setLastname("lastname");
        author.setFirstname("firstname");
        author.setEmail("email@email.com");

        return author;
    }

    @Test
    public void shouldAcceptEmptyList() {
        //given
        List<Publication> publications = newArrayList();

        //when
        String print = printer.print(publications);

        //then
        assertThat(print).isEmpty();
    }

    @Test
    public void shouldPrintBook() {
        //given
        Book book = new Book();
        book.setTitle("title");
        book.setAuthors(newArrayList("author1", "author2"));
        book.setIsbn("isbn");
        book.setDescription("description");

        //when
        String print = printer.print(book);

        //then
        assertThat(print).isEqualTo("Title: title\n" +
                "ISBN: isbn\n" +
                "Description: description\n" +
                "Authors: \n" +
                "\t- firstname lastname (email@email.com)\n" +
                "\t- firstname lastname (email@email.com)");
    }

    @Test
    public void shouldPrintMagazine() {
        //given
        Magazine magazine = new Magazine();
        magazine.setTitle("title");
        magazine.setAuthors(newArrayList("author1", "author2"));
        magazine.setIsbn("isbn");
        magazine.setPublishedAt("21.05.2011");

        //when
        String print = printer.print(magazine);

        //then
        assertThat(print).isEqualTo("Title: title\n" +
                "ISBN: isbn\n" +
                "Published at: 21.05.2011\n" +
                "Authors: \n" +
                "\t- firstname lastname (email@email.com)\n" +
                "\t- firstname lastname (email@email.com)");
    }
}