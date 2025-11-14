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
    public ResponseEntity<MemberResponse> addMember(@Valid @RequestBody MemberRequest memberRequest) {
        MemberResponse response = memberService.addMember(memberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponse>> getBorrowedBooks(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getBorrowedBooks(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
