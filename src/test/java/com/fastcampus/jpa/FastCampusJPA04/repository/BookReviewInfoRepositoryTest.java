package com.fastcampus.jpa.FastCampusJPA04.repository;

import com.fastcampus.jpa.FastCampusJPA04.domain.Book;
import com.fastcampus.jpa.FastCampusJPA04.domain.BookReviewInfo;
import com.fastcampus.jpa.FastCampusJPA04.domain.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BookReviewInfoRepositoryTest {
    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void crudTest(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);
        bookReviewInfoRepository.save(bookReviewInfo);
        System.out.println(">>>>" + bookReviewInfoRepository.findAll());
    }

    @Test
    public void crudTest2(){
        givenBookReviewInfo();
        Book result = bookReviewInfoRepository
                        .findById(1L)
                        .orElseThrow(RuntimeException::new)
                        .getBook();
        System.out.println(">>> " + result);

        BookReviewInfo result2 = bookRepository
                .findById(1L)
                .orElseThrow(RuntimeException::new)
                .getBookReviewInfo();
        System.out.println(">>> "+ result2);
    }

    private Book givenBook(){
        Book book = new Book();
        book.setName("Jpa 초격차 패키지");
        book.setAuthorId(1L);
        return bookRepository.save(book);
    }

    private void givenBookReviewInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);
        System.out.println(">>> " + bookReviewInfoRepository.findAll());
    }

    @Transactional
    @Test
    void bookCascadeTest(){
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        //중간 중간마다 영속성을 관리하기 위해 save를 하는게 이상하다.
        //cascade가 필요
        //bookRepository.save(book);//영속성 관리
        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");
        //publisherRepository.save(publisher);
        book.setPublisher(publisher);
        bookRepository.save(book);
        //오류 발생 , Entity 참조변수가 영속성 관리가 되지 않는 주소를 저장하기 때문에 오류가 발생
        //publisher.getBooks().add(book);
        //publisher.addBook(book);//가독성 있는 코드로 변경
        //publisherRepository.save(publisher);
        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publishers : "+publisherRepository.findAll());
        //오류 발생   1. hibernate.direct 옵션 (DB 연결이 안됬다.)
        //          2.could not initialize proxy - no Session 에러
        //            세션이 없다는뜻 = 트렌젝션이 없다는 뜻
        //            해결 = 상단에 Transactional 추가, toString의 exclude 추가

        //Update(Merge) Event가 발생한다.
        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("슬로우캠퍼스");

        bookRepository.save(book1);

        System.out.println("publishers : "+publisherRepository.findAll());

        Book book2 = bookRepository.findById(1L).get();
        //bookRepository.delete(book2);
        //bookRepository.deleteById(1L);

        //publisherRepository.delete(book2.getPublisher());

        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);

        bookRepository.save(book3);


        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());
        System.out.println("book3-publisher : "+ bookRepository.getById(1L).getPublisher());

    }
    @Test
    public void bookRemoveCascadeTest(){
        bookRepository.deleteById(1L);

        System.out.println("books : "+ bookRepository.findAll());
        System.out.println("publishers : "+ publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
    }

}