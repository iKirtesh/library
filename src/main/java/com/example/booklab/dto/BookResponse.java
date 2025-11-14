package com.example.booklab.dto;

import com.example.booklab.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Category category;
    private String publisher;
    private double price;
    private Long issuedToMemberId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
