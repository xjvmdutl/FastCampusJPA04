package com.fastcampus.jpa.FastCampusJPA04.repository;

import com.fastcampus.jpa.FastCampusJPA04.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
