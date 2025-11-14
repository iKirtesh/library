package com.example.booklab.service;

import com.example.booklab.dto.IssueRequest;
import com.example.booklab.exception.OperationNotAllowedException;
import com.example.booklab.exception.ResourceNotFoundException;
import com.example.booklab.model.Book;
import com.example.booklab.model.Member;
import com.example.booklab.repository.BookRepository;
import com.example.booklab.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BookService bookService;

    @Transactional
    public void issueBook(IssueRequest issueRequest) {
        Book book = bookService.findBookById(issueRequest.getBookId());
        Member member = memberRepository.findById(issueRequest.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + issueRequest.getMemberId()));

        if (book.getMember() != null) {
            throw new OperationNotAllowedException("Book is already issued to another member");
        }

        book.setMember(member);
        member.getBooks().add(book);
    }

    @Transactional
    public void returnBook(Long bookId) {
        Book book = bookService.findBookById(bookId);
        if (book.getMember() == null) {
            throw new OperationNotAllowedException("Book is not currently issued");
        }

        Member member = book.getMember();
        member.getBooks().remove(book);
        book.setMember(null);
    }
}
