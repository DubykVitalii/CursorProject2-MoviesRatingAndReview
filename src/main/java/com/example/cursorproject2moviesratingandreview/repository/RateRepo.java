package com.example.cursorproject2moviesratingandreview.repository;

import com.example.cursorproject2moviesratingandreview.models.Movie;
import com.example.cursorproject2moviesratingandreview.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepo extends JpaRepository<Rate, Long> {
    boolean existsByMovie(Movie movie);
    Rate getRateByMovie(Movie movie);
}
