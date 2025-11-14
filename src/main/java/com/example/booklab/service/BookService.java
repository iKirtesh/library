package com.example.booklab.service;

import com.example.booklab.dto.BookRequest;
import com.example.booklab.dto.BookResponse;
import com.example.booklab.exception.ResourceNotFoundException;
import com.example.booklab.model.Book;
import com.example.booklab.model.Category;
import com.example.booklab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookResponse addBook(BookRequest bookRequest) {
        Book book = modelMapper.map(bookRequest, Book.class);
        if (bookRepository.findByTitle(book.getTitle()).isPresent()) {
            throw new IllegalStateException("A book with title " + book.getTitle() + " already exists.");
        }
        return convertToDto(bookRepository.save(book));
    }

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto).toList();
    }

    public BookResponse getBookById(Long id) {
        Book book = findBookById(id);
        return convertToDto(book);
    }

    @Transactional
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
		Book book = findBookById(id);
		modelMapper.map(bookRequest, book);
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
                .map(this::convertToDto).toList();
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
