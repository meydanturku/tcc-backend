package com.tcc.book.enums;

import lombok.Getter;

@Getter
public enum CommonValues {
    BOOK_NOT_FOUND("Book not found"),
    BOOK_RETRIEVED_SUCCESSFULLY("Book retrieved successfully"),
    BOOK_CREATED_SUCCESSFULLY("Book created successfully"),
    BOOK_UPDATED_SUCCESSFULLY("Book updated successfully"),
    BOOK_DELETED_SUCCESSFULLY("Book deleted successfully"),
    BOOKS_RETRIEVED_SUCCESSFULLY("Books retrieved successfully"),
    BOOKS_AI_DESCRIPTION_SUCCESSFULLY("Books description retrieved successfully");

    private final String message;

    CommonValues(String message) {
        this.message = message;
    }
}
