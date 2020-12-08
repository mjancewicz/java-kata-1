package org.echocat.kata.java.part1.repository;

import lombok.AllArgsConstructor;
import org.echocat.kata.java.part1.model.Magazine;

import java.util.List;

@AllArgsConstructor
public class MagazineRepository {
    private List<Magazine> rows;

    public List<Magazine> findAll() {
        return rows;
    }
}