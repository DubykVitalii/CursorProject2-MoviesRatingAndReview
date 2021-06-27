package com.example.cursorproject2moviesratingandreview.service;

import com.example.cursorproject2moviesratingandreview.models.Movie;
import com.example.cursorproject2moviesratingandreview.models.Rate;
import com.example.cursorproject2moviesratingandreview.repository.RateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateService {
    RateRepo rateRepo;
    MovieService movieService;

    @Autowired
    public RateService(RateRepo rateRepo, MovieService movieService) {
        this.rateRepo = rateRepo;
        this.movieService = movieService;
    }

    public Rate createRate(Long filmId, double rateValue) {
        Movie movie = movieService.getMovieById(filmId);
        Rate rate = new Rate(rateValue, movie);
        rate.setCountOfVotes(1);
        rateRepo.save(rate);
        return rate;
    }

    public void update(Rate rate) {
        rateRepo.save(rate);
    }


    public boolean existsByMovie(Long filmId) {
        Movie movie = movieService.getMovieById(filmId);
        return rateRepo.existsByMovie(movie);
    }

    public Rate getRateByIdMovie(Long filmId) {
        Movie movie = movieService.getMovieById(filmId);
        return rateRepo.getRateByMovie(movie);
    }

    public void deleteRateByMovie(Movie movie) {
        Rate rate = rateRepo.getRateByMovie(movie);
        if (rate != null)
            rateRepo.delete(rate);
    }
}
