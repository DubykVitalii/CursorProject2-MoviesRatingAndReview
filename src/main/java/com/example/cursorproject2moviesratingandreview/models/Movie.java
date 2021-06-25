package com.example.cursorproject2moviesratingandreview.models;


import com.example.cursorproject2moviesratingandreview.models.enums.Category;

public class Movie {
    private int id;
    private  String name;
    private Category category;
    private String director;
    private String shortDescription;
    private Review rateValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Review getRateValue() {
        return rateValue;
    }

    public void setRateValue(Review rateValue) {
        this.rateValue = rateValue;
    }
}
