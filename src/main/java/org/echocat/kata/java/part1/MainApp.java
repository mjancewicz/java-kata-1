package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.model.factory.AuthorFactory;
import org.echocat.kata.java.part1.model.factory.BookFactory;
import org.echocat.kata.java.part1.model.factory.MagazineFactory;
import org.echocat.kata.java.part1.repository.AuthorRepository;
import org.echocat.kata.java.part1.repository.BookRepository;
import org.echocat.kata.java.part1.repository.MagazineRepository;
import org.echocat.kata.java.part1.repository.PublicationRepository;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws IOException {
        AuthorFactory authorFactory = new AuthorFactory();
        BookFactory bookFactory = new BookFactory();
        MagazineFactory magazineFactory = new MagazineFactory();
        CsvParser csvParser = new CsvParser();
        
        AuthorRepository authorRepository = new AuthorRepository(
                csvParser.parse(MainApp.class.getResourceAsStream(
                        "/org/echocat/kata/java/part1/data/authors.csv"
                ), authorFactory::create)
        );
        BookRepository bookRepository = new BookRepository(
                csvParser.parse(MainApp.class.getResourceAsStream(
                        "/org/echocat/kata/java/part1/data/books.csv"
                ), bookFactory::create)
        );
        MagazineRepository magazineRepository = new MagazineRepository(
                csvParser.parse(MainApp.class.getResourceAsStream(
                        "/org/echocat/kata/java/part1/data/magazines.csv"
                ), magazineFactory::create)
        );

        PublicationRepository publicationRepository = new PublicationRepository(bookRepository, magazineRepository);

        Library library = new Library(publicationRepository);
        Printer printer = new Printer(authorRepository);

        System.out.println(printer.print(library.findAll()));

        library.findByIsbn("1313-4545-8875")
                .ifPresent(publication -> System.out.print(printer.print(publication)));

        System.out.print(printer.print(library.findByAuthor("null-walter@echocat.org")));

        System.out.print(printer.print(library.findByOrderByTitle()));
    }
}
