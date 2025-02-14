package com.tcc.book.service;

import com.tcc.book.dto.request.BookRequestDTO;
import com.tcc.book.dto.response.BookResponseDTO;
import com.tcc.book.entity.Book;
import com.tcc.book.repository.BookRepository;
import com.tcc.book.utils.Response;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private DozerBeanMapper dozerBeanMapper;
    private BookRequestDTO bookRequest;
    private Book book;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        dozerBeanMapper = new DozerBeanMapper();

        bookService = new BookService(bookRepository, dozerBeanMapper);

        bookRequest = new BookRequestDTO();
        bookRequest.setTitle("Test Book");
        bookRequest.setAuthor("Test Author");
        bookRequest.setIsbn("123456789");
        bookRequest.setPublicationYear(2024);
        bookRequest.setDescription("This is a test book description.");

        book = new Book();
        book.setId(1L);
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setPublicationYear(bookRequest.getPublicationYear());
        book.setDescription(bookRequest.getDescription());
    }

    @Test
    public void shouldGetBookByIdSuccessfully() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        Response<BookResponseDTO> response = bookService.getBookById(1L);

        assertNotNull(response.getData());
        assertFalse(response.getErrorExist());
        assertEquals("Test Book", response.getData().getTitle());
    }

    @Test
    public void shouldReturnErrorForNonExistingBook() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> bookService.getBookById(999L));
    }

    @Test
    public void shouldCreateBookSuccessfully() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Response<BookResponseDTO> response = bookService.createBook(bookRequest);

        assertNotNull(response.getData());
        assertEquals("Test Book", response.getData().getTitle());
        assertFalse(response.getErrorExist());
    }
}
