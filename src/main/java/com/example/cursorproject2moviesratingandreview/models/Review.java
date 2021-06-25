package com.example.cursorproject2moviesratingandreview.models;


public class Review {
    private int idOfMovie;
    private String reviewMessage;
    private boolean isLiked;

    public int getIdOfMovie() {
        return idOfMovie;
    }

    public void setIdOfMovie(int idOfMovie) {
        this.idOfMovie = idOfMovie;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public void setReviewMessage(String reviewMessage) {
        this.reviewMessage = reviewMessage;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
