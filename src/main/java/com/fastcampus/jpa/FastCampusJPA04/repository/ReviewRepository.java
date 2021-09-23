package com.fastcampus.jpa.FastCampusJPA04.repository;

import com.fastcampus.jpa.FastCampusJPA04.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
