package com.tcc.book.controller;

import com.tcc.book.dto.request.BookRequestDTO;
import com.tcc.book.dto.response.BookResponseDTO;
import com.tcc.book.service.BookService;
import com.tcc.book.utils.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    private BookRequestDTO bookRequest;
    private BookResponseDTO bookResponse;

    @BeforeEach
    void setup() {

        bookResponse = new BookResponseDTO();
        bookResponse.setTitle(bookRequest.getTitle());
        bookResponse.setAuthor(bookRequest.getAuthor());
        bookResponse.setIsbn(bookRequest.getIsbn());
        bookResponse.setPublicationYear(bookRequest.getPublicationYear());
        bookResponse.setDescription(bookRequest.getDescription());
    }

    @Test
    public void shouldCreateBookSuccessfully() throws Exception {
        Response<BookResponseDTO> response = new Response<>();
        response.setData(bookResponse);
        response.setMessage("Book created successfully");
        response.setErrorExist(false);

        when(bookService.createBook(any(BookRequestDTO.class))).thenReturn(response);

        assertNotNull(response.getData(), "Response data should not be null");

    }
}
