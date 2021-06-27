package com.example.cursorproject2moviesratingandreview.repository;

import com.example.cursorproject2moviesratingandreview.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
    Movie getMovieByName(String name);
}
