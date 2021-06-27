package com.example.cursorproject2moviesratingandreview.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table (name = "rates")
@Data
public class Rate {

    @Id
    private Long id;
    @Column(name = "RateValue")
    private double rateValue;
    @Column(name = "CountOfVotes")
    private int countOfVotes;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Movie movie;

    public Rate(double rateValue,  Movie movie) {
        this.rateValue = rateValue;
        this.movie = movie;
    }

    public Rate(double rateValue) {
        this.rateValue = rateValue;
    }

    public Rate() {
    }
}
