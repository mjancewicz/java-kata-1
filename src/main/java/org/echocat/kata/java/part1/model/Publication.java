package org.echocat.kata.java.part1.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class Publication {
    private String title;
    private String isbn;
    private List<String> authors;
}