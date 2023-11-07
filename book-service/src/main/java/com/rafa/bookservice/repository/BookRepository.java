package com.rafa.bookservice.repository;

import com.rafa.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book getBookById(Long id);
}
