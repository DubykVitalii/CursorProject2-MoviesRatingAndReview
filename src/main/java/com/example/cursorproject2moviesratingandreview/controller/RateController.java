package com.example.cursorproject2moviesratingandreview.controller;

import com.example.cursorproject2moviesratingandreview.dto.RateDto;
import com.example.cursorproject2moviesratingandreview.models.Rate;
import com.example.cursorproject2moviesratingandreview.service.RateService;
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
public class RateController {

    private RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping(
            value = "/movie/rate/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Rate> createRate(
            @PathVariable("id") Long filmId,
            @RequestBody final RateDto rateDto
    ) {
        final Rate rate;
        if (!rateService.existsByMovie(filmId)) {
            rate = rateService.createRate(filmId, rateDto.getRateValue());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            rate = rateService.getRateByIdMovie(filmId);
            rate.setCountOfVotes(rate.getCountOfVotes() + 1);
            rate.setRateValue((rateDto.getRateValue() + rate.getRateValue() * (rate.getCountOfVotes() - 1)) / rate.getCountOfVotes());
            rateService.update(rate);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
