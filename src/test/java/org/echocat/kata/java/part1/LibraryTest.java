package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Publication;
import org.echocat.kata.java.part1.repository.PublicationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.echocat.kata.java.part1.testdata.PublicationMother.mockBook;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LibraryTest {

    @Mock
    private PublicationRepository publicationRepository;
    @InjectMocks
    private Library library;

    @Test
    public void shouldReturnSorted() {
        //given
        Book book2 = mockBook("book2");
        Book book1 = mockBook("book1");
        when(publicationRepository.findAll()).thenReturn(newArrayList(book2, book1));

        //when
        List<Publication> sorted = library.findByOrderByTitle();

        //then
        assertThat(sorted).containsExactly(book1, book2);
    }

    @Test
    public void shouldFindByIban() {
        //given
        Book expectedBook = mockBook("book");
        when(publicationRepository.findByIsbn(any())).thenReturn(of(expectedBook));

        //when
        Optional<Publication> actualBook = library.findByIsbn("isbn");

        //then
        assertThat(actualBook).hasValue(expectedBook);
    }

    @Test
    public void shouldFindByAuthor() {
        //given
        Book expectedBook = mockBook("book");
        when(publicationRepository.findByAuthor(any())).thenReturn(newArrayList(expectedBook));

        //when
        List<Publication> actualBooks = library.findByAuthor("email");

        //then
        assertThat(actualBooks)
                .hasSize(1)
                .element(0)
                .isEqualTo(expectedBook);
    }
}
