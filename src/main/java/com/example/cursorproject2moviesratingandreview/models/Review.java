package com.example.cursorproject2moviesratingandreview.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Message")
    private String message;
    @Column(name = "Likes")
    private Boolean likes;

    @OneToOne(fetch = FetchType.LAZY)
    private Movie movie;

    public Review(String message, boolean likes, Movie movie) {
        this.message = message;
        this.likes = likes;
        this.movie = movie;
    }

    public Review(String message, boolean likes) {
        this.message = message;
        this.likes = likes;
    }

    public Review() {
    }


}
