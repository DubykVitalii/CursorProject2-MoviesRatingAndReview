package com.example.cursorproject2moviesratingandreview.service;

import com.example.cursorproject2moviesratingandreview.models.Movie;
import com.example.cursorproject2moviesratingandreview.models.Review;
import com.example.cursorproject2moviesratingandreview.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    ReviewRepo reviewRepo;
    MovieService movieService;

    @Autowired
    public ReviewService(ReviewRepo reviewRepo, MovieService movieService) {
        this.reviewRepo = reviewRepo;
        this.movieService = movieService;
    }

    public void createReview(String message, boolean likes, Long filmId) {
        Movie movie = movieService.getMovieById(filmId);
        Review review = new Review(message, likes, movie);
        reviewRepo.save(review);
    }

    public List<Review> getAllReviewByMovie(Movie movie) {
        return reviewRepo.getAllReviewByMovie(movie);
    }

    public void deleteReviewByMovie(Movie movie) {
        List<Review> listReview = getAllReviewByMovie(movie);
        for (int i = 0; i < listReview.size(); i++) {
            reviewRepo.delete(listReview.get(i));
        }
    }
}
