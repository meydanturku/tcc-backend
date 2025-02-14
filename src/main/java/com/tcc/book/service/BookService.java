package com.tcc.book.service;

import com.tcc.book.dto.request.BookRequestDTO;
import com.tcc.book.dto.response.BookResponseDTO;
import com.tcc.book.entity.Book;
import com.tcc.book.enums.CommonValues;
import com.tcc.book.exception.BookNotFoundException;
import com.tcc.book.repository.BookRepository;
import com.tcc.book.utils.Response;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final DozerBeanMapper dozerBeanMapper;

    public Response<List<BookResponseDTO>> getAllBooks() {
        Response<List<BookResponseDTO>> response = new Response<>();
        List<Book> books = bookRepository.findAll();

        List<BookResponseDTO> bookResponseDTOs = books.stream()
                .map(book -> dozerBeanMapper.map(book, BookResponseDTO.class))
                .collect(Collectors.toList());

        response.setData(bookResponseDTOs);
        response.setMessage(CommonValues.BOOKS_RETRIEVED_SUCCESSFULLY.getMessage());
        response.setErrorExist(false);
        return response;
    }

    public Response<BookResponseDTO> getBookById(Long id) {
        Response<BookResponseDTO> response = new Response<>();

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(CommonValues.BOOK_NOT_FOUND.getMessage()));

        BookResponseDTO bookResponseDTO = dozerBeanMapper.map(book, BookResponseDTO.class);
        response.setData(bookResponseDTO);
        response.setMessage(CommonValues.BOOK_RETRIEVED_SUCCESSFULLY.getMessage());
        response.setErrorExist(false);
        return response;
    }

    public Response<BookResponseDTO> createBook(BookRequestDTO bookRequestDTO) {
        Response<BookResponseDTO> response = new Response<>();

        Book book = dozerBeanMapper.map(bookRequestDTO, Book.class);
        Book savedBook = bookRepository.save(book);

        BookResponseDTO bookResponseDTO = dozerBeanMapper.map(savedBook, BookResponseDTO.class);
        response.setData(bookResponseDTO);
        response.setMessage(CommonValues.BOOK_CREATED_SUCCESSFULLY.getMessage());
        response.setErrorExist(false);
        return response;
    }

    public Response<BookResponseDTO> updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Response<BookResponseDTO> response = new Response<>();

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(CommonValues.BOOK_NOT_FOUND.getMessage()));

        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setPublicationYear(bookRequestDTO.getPublicationYear());
        book.setDescription(bookRequestDTO.getDescription());

        Book updatedBook = bookRepository.save(book);
        BookResponseDTO bookResponseDTO = dozerBeanMapper.map(updatedBook, BookResponseDTO.class);

        response.setData(bookResponseDTO);
        response.setMessage(CommonValues.BOOK_UPDATED_SUCCESSFULLY.getMessage());
        response.setErrorExist(false);
        return response;
    }

    public Response<Void> deleteBook(Long id) {
        Response<Void> response = new Response<>();

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(CommonValues.BOOK_NOT_FOUND.getMessage()));

        bookRepository.delete(book);
        response.setMessage(CommonValues.BOOK_DELETED_SUCCESSFULLY.getMessage());
        response.setErrorExist(false);
        return response;
    }
}
