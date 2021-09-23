package com.fastcampus.jpa.FastCampusJPA04.repository;

import com.fastcampus.jpa.FastCampusJPA04.domain.BookAndAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAndAuthorRepository extends JpaRepository<BookAndAuthor,Long> {
}
