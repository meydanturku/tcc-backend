package com.tcc.book.dto.response;

import lombok.Data;

@Data
public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private String description;
}
