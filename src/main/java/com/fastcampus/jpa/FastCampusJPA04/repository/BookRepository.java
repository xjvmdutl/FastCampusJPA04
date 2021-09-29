package com.fastcampus.jpa.FastCampusJPA04.repository;

import com.fastcampus.jpa.FastCampusJPA04.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Modifying
    @Query(value = "update book set category='none'",nativeQuery = true)
    public void update();

    List<Book> findByCategoryIsNull();

    List<Book> findAllByDeletedFalse();//삭제 내역은 안보여줌

    List<Book> findByCategoryIsNullAndDeletedFalse();
}
