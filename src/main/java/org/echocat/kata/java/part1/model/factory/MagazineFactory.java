package org.echocat.kata.java.part1.model.factory;

import org.echocat.kata.java.part1.model.Magazine;

import java.util.Map;

import static com.google.common.base.Splitter.on;

public class MagazineFactory {
    public Magazine create(Map<String, String> rowCells) {
        Magazine magazine = new Magazine();
        magazine.setTitle(rowCells.get("title"));
        magazine.setIsbn(rowCells.get("isbn"));
        magazine.setAuthors(on(",").splitToList(rowCells.get("authors")));
        magazine.setPublishedAt(rowCells.get("publishedAt"));

        return magazine;
    }
}
