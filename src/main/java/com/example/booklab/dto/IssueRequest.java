package com.example.booklab.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueRequest {
    @NotNull(message = "Book ID is required")
    private Long bookId;
    
    @NotNull(message = "Member ID is required")
    private Long memberId;
}
