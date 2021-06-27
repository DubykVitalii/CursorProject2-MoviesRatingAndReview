package com.example.cursorproject2moviesratingandreview.controller;

import com.example.cursorproject2moviesratingandreview.dto.MovieDto;
import com.example.cursorproject2moviesratingandreview.dto.RateDto;
import com.example.cursorproject2moviesratingandreview.dto.ReviewDto;
import com.example.cursorproject2moviesratingandreview.models.Movie;
import com.example.cursorproject2moviesratingandreview.models.Rate;
import com.example.cursorproject2moviesratingandreview.models.Review;
import com.example.cursorproject2moviesratingandreview.models.enums.Category;
import com.example.cursorproject2moviesratingandreview.service.MovieService;
import com.example.cursorproject2moviesratingandreview.service.RateService;
import com.example.cursorproject2moviesratingandreview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private MovieService movieService;
    private ReviewService reviewService;
    private RateService rateService;

    @Autowired
    public MovieController(MovieService movieService, ReviewService reviewService, RateService rateService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
        this.rateService = rateService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping(
            value = "/movie/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MovieDto> getMovie(
            @PathVariable("id") Long filmId
    ) {
        final Movie movie = movieService.getMovieById(filmId);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        MovieDto movieDto = new MovieDto();
        movieDto.setName(movie.getName());
        movieDto.setCategory(movie.getCategory());
        movieDto.setDirector(movie.getDirector());
        movieDto.setDescription(movie.getDescription());

        List<Review> review = reviewService.getAllReviewByMovie(movie);
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for (int i = 0; i < review.size(); i++) {
            reviewDtoList.add(new ReviewDto(review.get(i).getMessage(), review.get(i).getLikes()));
        }
        movieDto.setReviewList(reviewDtoList);

        Rate rate = rateService.getRateByIdMovie(filmId);
        RateDto rateDto = new RateDto();
        if (rate != null) {
            rateDto.setRateValue(rate.getRateValue());
            rateDto.setCountOfVotes(rate.getCountOfVotes());
        } else {
            rateDto.setRateValue(0);
            rateDto.setCountOfVotes(0);
        }
        movieDto.setRate(rateDto);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping(
            value = "/admin/movie/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Movie> createMovie(
            @PathVariable("id") Long filmId,
            @RequestBody final MovieDto movieDto
    ) {
        movieService.update(filmId, movieDto.getName(), movieDto.getCategory(), movieDto.getDirector(), movieDto.getDescription());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(
            value = "/admin/movie/delete/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Movie> deleteMovie(
            @PathVariable("id") Long filmId
    ) {

        final Movie movie = movieService.getMovieById(filmId);
        rateService.deleteRateByMovie(movie);
        reviewService.deleteReviewByMovie(movie);
        movieService.delete(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping(
            value = "/admin/movie/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Movie> createMovie(
            @RequestBody final MovieDto movieDto
    ) {
        movieService.createMovie(movieDto.getName(), movieDto.getCategory(), movieDto.getDirector(), movieDto.getDescription());
        final Movie movie = movieService.getMovieByName(movieDto.getName());
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping(
            value = "/movie/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<MovieDto>> getAllMovie() {
        List<MovieDto> movieDtoList = new ArrayList<>();
        List<Movie> allMovies = movieService.getAllMovie();
        for (int i = 0; i < allMovies.size(); i++) {
            MovieDto movieDto = new MovieDto();
            movieDto.setName(allMovies.get(i).getName());
            movieDto.setCategory(allMovies.get(i).getCategory());
            movieDto.setDirector(allMovies.get(i).getDirector());
            movieDto.setDescription(allMovies.get(i).getDescription());
            Rate rate = rateService.getRateByIdMovie(allMovies.get(i).getId());
            RateDto rateDto = new RateDto();
            if (rate != null) {
                rateDto.setRateValue(rate.getRateValue());
                rateDto.setCountOfVotes(rate.getCountOfVotes());
                movieDto.setRate(rateDto);
            } else {
                rateDto.setRateValue(0);
                rateDto.setCountOfVotes(0);
            }
            movieDto.setRate(rateDto);
            movieDtoList.add(movieDto);
        }
        return new ResponseEntity<>(movieDtoList, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping(
            value = "/movie/rating",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<MovieDto>> getMoviesByRating() {
        List<MovieDto> movieDtoList = new ArrayList<>();
        List<Movie> allMovies = movieService.getAllMovie();
        for (int i = 0; i < allMovies.size(); i++) {
            MovieDto movieDto = new MovieDto();
            movieDto.setName(allMovies.get(i).getName());
            movieDto.setCategory(allMovies.get(i).getCategory());
            movieDto.setDirector(allMovies.get(i).getDirector());
            movieDto.setDescription(allMovies.get(i).getDescription());
            Rate rate = rateService.getRateByIdMovie(allMovies.get(i).getId());
            RateDto rateDto = new RateDto();
            if (rate != null) {
                rateDto.setRateValue(rate.getRateValue());
                rateDto.setCountOfVotes(rate.getCountOfVotes());
                movieDto.setRate(rateDto);
            } else {
                rateDto.setRateValue(0);
                rateDto.setCountOfVotes(0);
            }
            movieDto.setRate(rateDto);
            movieDtoList.add(movieDto);
        }
        movieDtoList.sort(Comparator.comparing(f -> f.getRate().getRateValue()));
        Collections.reverse(movieDtoList);
        return new ResponseEntity<>(movieDtoList, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping(
            value = "/movie/category/{category}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<MovieDto>> getMoviesByCategory(
            @PathVariable("category") Category category
    ) {
        List<MovieDto> movieDtoList = new ArrayList<>();
        List<Movie> allMovies = movieService.getAllMovie();
        for (int i = 0; i < allMovies.size(); i++) {
            MovieDto movieDto = new MovieDto();
            movieDto.setName(allMovies.get(i).getName());
            movieDto.setCategory(allMovies.get(i).getCategory());
            movieDto.setDirector(allMovies.get(i).getDirector());
            movieDto.setDescription(allMovies.get(i).getDescription());
            Rate rate = rateService.getRateByIdMovie(allMovies.get(i).getId());
            RateDto rateDto = new RateDto();
            if (rate != null) {
                rateDto.setRateValue(rate.getRateValue());
                rateDto.setCountOfVotes(rate.getCountOfVotes());
                movieDto.setRate(rateDto);
            } else {
                rateDto.setRateValue(0);
                rateDto.setCountOfVotes(0);
            }
            movieDto.setRate(rateDto);
            movieDtoList.add(movieDto);
        }
        movieDtoList = movieDtoList.stream().filter(c -> c.getCategory().equals(category)).collect(Collectors.toList());
        return new ResponseEntity<>(movieDtoList, HttpStatus.OK);
    }
}
