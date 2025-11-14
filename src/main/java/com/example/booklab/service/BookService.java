package com.example.booklab.service;

import com.example.booklab.dto.BookRequest;
import com.example.booklab.dto.BookResponse;
import com.example.booklab.exception.ResourceNotFoundException;
import com.example.booklab.model.Book;
import com.example.booklab.model.Category;
import com.example.booklab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public BookResponse addBook(BookRequest bookRequest) {
        Book book = modelMapper.map(bookRequest, Book.class);
        book = bookRepository.save(book);
        return convertToDto(book);
    }

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(Long id) {
        Book book = findBookById(id);
        return convertToDto(book);
    }

    @Transactional
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Book book = findBookById(id);
        modelMapper.map(bookRequest, book);
        book = bookRepository.save(book);
        return convertToDto(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = findBookById(id);
        if (book.getMember() != null) {
            throw new IllegalStateException("Cannot delete a book that is currently issued");
        }
        bookRepository.delete(book);
    }

    public List<BookResponse> getBooksByCategory(Category category) {
        return bookRepository.findByCategory(category).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    BookResponse convertToDto(Book book) {
        BookResponse response = modelMapper.map(book, BookResponse.class);
        if (book.getMember() != null) {
            response.setIssuedToMemberId(book.getMember().getId());
        }
        return response;
    }
}
