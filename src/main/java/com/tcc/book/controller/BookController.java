package com.tcc.book.controller;

import com.tcc.book.dto.request.BookRequestDTO;
import com.tcc.book.dto.response.BookResponseDTO;
import com.tcc.book.entity.Book;
import com.tcc.book.service.AIService;
import com.tcc.book.service.BookService;
import com.tcc.book.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AIService aiService;

    @GetMapping
    public ResponseEntity<Response<List<BookResponseDTO>>> getAllBooks() {
        Response<List<BookResponseDTO>> response = bookService.getAllBooks();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<BookResponseDTO>> getBookById(@PathVariable Long id) {
        Response<BookResponseDTO> response = bookService.getBookById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<BookResponseDTO>> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        Response<BookResponseDTO> response = bookService.createBook(bookRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<BookResponseDTO>> updateBook(@PathVariable Long id,
                                                                @RequestBody BookRequestDTO bookRequestDTO) {
        Response<BookResponseDTO> response = bookService.updateBook(id, bookRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteBook(@PathVariable Long id) {
        Response<Void> response = bookService.deleteBook(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/ai-insights")
    public ResponseEntity<Response<String>> getBookAIInsights(@PathVariable Long id) {
        Response<BookResponseDTO> bookById = bookService.getBookById(id);
        Response<String> insight = aiService.generateBookInsight(bookById.getData().getDescription());
        return ResponseEntity.ok(insight);
    }
}
