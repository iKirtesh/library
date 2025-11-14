package com.example.booklab.dto;

import com.example.booklab.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Author is required")
    private String author;
    
    @NotNull(message = "Category is required")
    private Category category;
    
    @NotBlank(message = "Publisher is required")
    private String publisher;
    
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;
}
