package com.fastcampus.jpa.FastCampusJPA04.service;

import com.fastcampus.jpa.FastCampusJPA04.domain.Author;
import com.fastcampus.jpa.FastCampusJPA04.domain.Book;
import com.fastcampus.jpa.FastCampusJPA04.repository.AuthorRepository;
import com.fastcampus.jpa.FastCampusJPA04.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public void put(){
        this.putBookAndAuthor();
    }


    //두개의 Transactional 존재(javax : Spring 의존성이 없다,spring : Spring 기능 사용가능)
    //@Transactional(rollbackFor = Exception.class)
    @Transactional
    private void putBookAndAuthor(){
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

        //throw new RuntimeException("오류가 나서 DB Commit이 발생하지 않습니다.");
        throw new RuntimeException("오류가 나서 DB Commit이 발생하지 않습니다.");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void get(Long id){
        System.out.println(">>> "+ bookRepository.findById(id));
        System.out.println(">>> "+ bookRepository.findAll());

        System.out.println(">>> "+ bookRepository.findById(id));
        System.out.println(">>> "+ bookRepository.findAll());

        Book book = bookRepository.findById(id).get();
        book.setName("바뀔까?");
        bookRepository.save(book);
    }
}
