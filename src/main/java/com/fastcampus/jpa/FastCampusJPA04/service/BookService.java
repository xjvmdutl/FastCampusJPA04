package com.fastcampus.jpa.FastCampusJPA04.service;

import com.fastcampus.jpa.FastCampusJPA04.domain.Author;
import com.fastcampus.jpa.FastCampusJPA04.domain.Book;
import com.fastcampus.jpa.FastCampusJPA04.repository.AuthorRepository;
import com.fastcampus.jpa.FastCampusJPA04.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional//두개의 Transactional 존재(javax : Spring 의존성이 없다,spring : Spring 기능 사용가능)
    public void putBookAndAuthor() throws Exception{
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

        //throw new RuntimeException("오류가 나서 DB Commit이 발생하지 않습니다.");
        throw new Exception("오류가 나서 DB Commit이 발생하지 않습니다.");
    }

}
