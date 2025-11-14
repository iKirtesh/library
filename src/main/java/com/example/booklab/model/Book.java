package com.example.booklab.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String author;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    @Column(nullable = false)
    private String publisher;
    
    @Column(nullable = false)
    @Positive
    private double price;
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Book(String title, String author, Category category, String publisher, double price) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.price = price;
    }
}
