package com.example.cursorproject2moviesratingandreview.service;

import com.example.cursorproject2moviesratingandreview.models.Movie;
import com.example.cursorproject2moviesratingandreview.models.enums.Category;
import com.example.cursorproject2moviesratingandreview.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepo movieRepo;

    @Autowired
    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public void save(Movie movie) {
        movieRepo.save(movie);
    }

    public void createMovie(String name, Category category, String director, String description) {
        Movie movie = new Movie(name, category, director, description);
        movieRepo.save(movie);
    }

    public Movie getMovieById(Long id) {
        return movieRepo.getById(id);
    }

    public void delete(Movie movie) {
        movieRepo.delete(movie);
    }

    public Movie getMovieByName(String name) {
        return movieRepo.getMovieByName(name);
    }

    public void update(Long id, String name, Category category, String director, String description) {
        Movie movie = movieRepo.getById(id);
        movie.setName(name);
        movie.setCategory(category);
        movie.setDirector(director);
        movie.setDescription(description);
        movieRepo.save(movie);
    }

    public List<Movie> getAllMovie() {
        return movieRepo.findAll();
    }
}
