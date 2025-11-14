package com.example.booklab.controller;

import com.example.booklab.dto.BookResponse;
import com.example.booklab.dto.MemberRequest;
import com.example.booklab.dto.MemberResponse;
import com.example.booklab.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse addMember(@Valid @RequestBody MemberRequest memberRequest) {
        return memberService.addMember(memberRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MemberResponse> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("/{id}/books")
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getBorrowedBooks(@PathVariable Long id) {
        return memberService.getBorrowedBooks(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
