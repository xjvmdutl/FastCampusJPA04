package com.fastcampus.jpa.FastCampusJPA04.repository;

import com.fastcampus.jpa.FastCampusJPA04.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;


    @Test
    public void bookTest(){
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setAuthorId(1L);
        //book.setPublisherId(1L);

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());
    }

    @Test
    public void softDelete(){
        bookRepository.findAll().forEach(System.out::println);
        System.out.println(bookRepository.findById(3L));
        //삭제된 데이터가 나오게 된다.

        //bookRepository.findByCategoryIsNull().forEach(System.out::println);

        //bookRepository.findAllByDeletedFalse().forEach(System.out::println);
        //bookRepository.findByCategoryIsNullAndDeletedFalse().forEach(System.out::println);
    }

}
