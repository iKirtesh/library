package com.example.booklab.dto;

import com.example.booklab.model.MembershipType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String name;
    private String email;
    private MembershipType membershipType;
    private List<BookResponse> borrowedBooks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
