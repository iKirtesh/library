package com.example.booklab.controller;

import com.example.booklab.dto.IssueRequest;
import com.example.booklab.service.LibraryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @PostMapping("/issue")
    @ResponseStatus(HttpStatus.OK)
    public void issueBook(@Valid @RequestBody IssueRequest issueRequest) {
        libraryService.issueBook(issueRequest);
    }

    @PostMapping("/return/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void returnBook(@PathVariable Long bookId) {
        libraryService.returnBook(bookId);
    }
}
