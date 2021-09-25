package com.fastcampus.jpa.FastCampusJPA04.service;

import com.fastcampus.jpa.FastCampusJPA04.repository.AuthorRepository;
import com.fastcampus.jpa.FastCampusJPA04.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void transactionTest(){
        try {
            bookService.putBookAndAuthor();
        }catch (Exception e){
            //여러 checked Exception, unchecked Exception이 섞여 발생된다.
            System.out.println(">>> " + e.getMessage());
        }
        System.out.println("books : "+bookRepository.findAll());
        System.out.println("authors : "+authorRepository.findAll());
    }
}