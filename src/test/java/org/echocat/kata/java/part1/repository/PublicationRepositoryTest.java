package org.echocat.kata.java.part1.repository;

import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazine;
import org.echocat.kata.java.part1.model.Publication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.echocat.kata.java.part1.testdata.PublicationMother.mockBook;
import static org.echocat.kata.java.part1.testdata.PublicationMother.mockMagazine;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PublicationRepositoryTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private MagazineRepository magazineRepository;
    @InjectMocks
    private PublicationRepository publicationRepository;

    private Book book = mockBook("bookTitle");
    private Magazine magazine = mockMagazine("magazineTitle");

    @Before
    public void before() {
        //given
        when(magazineRepository.findAll()).thenReturn(newArrayList(magazine));
        when(bookRepository.findAll()).thenReturn(newArrayList(book));
    }

    @Test
    public void shouldFindAll() {
        //when
        List<Publication> publications = publicationRepository.findAll();

        //then
        assertThat(publications).hasSize(2);
        assertThat(publications.get(0)).isEqualTo(book);
        assertThat(publications.get(1)).isEqualTo(magazine);
    }

    @Test
    public void shouldFindOneByAuthor() {
        //when
        List<Publication> publications = publicationRepository.findByAuthor("author1");

        //then
        assertThat(publications).hasSize(1);
        assertThat(publications.get(0)).isEqualTo(book);
    }

    @Test
    public void shouldFindNoByAuthor() {
        //when
        List<Publication> publications = publicationRepository.findByAuthor("author3");

        //then
        assertThat(publications).isEmpty();
    }

    @Test
    public void shouldFindByIsbn() {
        //when
        Optional<Publication> publication = publicationRepository.findByIsbn("magazineIsbn");

        //then
        assertThat(publication).hasValue(magazine);
    }
}