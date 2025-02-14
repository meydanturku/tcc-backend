package com.tcc.book.dto.request;

import lombok.Data;

@Data
public class BookRequestDTO {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private String description;
}
