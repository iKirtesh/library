package com.example.booklab.repository;

import java.util.List;
import java.util.Optional;

import com.example.booklab.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booklab.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByCategory(Category category);
	Optional<Object> findByTitle(String title);
}