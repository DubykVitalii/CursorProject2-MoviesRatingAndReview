package com.example.cursorproject2moviesratingandreview.controller;

import com.example.cursorproject2moviesratingandreview.dto.ReviewDto;
import com.example.cursorproject2moviesratingandreview.models.Rate;
import com.example.cursorproject2moviesratingandreview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping(
            value = "/movie/review/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Rate> createReview(
            @PathVariable("id") Long filmId,
            @RequestBody final ReviewDto reviewDto
    ) {

        reviewService.createReview(reviewDto.getMessage(), reviewDto.getLikes(), filmId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


