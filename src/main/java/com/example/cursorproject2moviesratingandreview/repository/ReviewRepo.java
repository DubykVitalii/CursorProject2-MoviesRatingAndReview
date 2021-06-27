package com.example.cursorproject2moviesratingandreview.repository;

import com.example.cursorproject2moviesratingandreview.models.Movie;
import com.example.cursorproject2moviesratingandreview.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> getAllReviewByMovie(Movie movie);
    void deleteListReviewByMovie(Movie movie);
}
