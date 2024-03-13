package com.leanPlateform.repository;

import com.leanPlateform.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByClientId(Long clientId);
}
