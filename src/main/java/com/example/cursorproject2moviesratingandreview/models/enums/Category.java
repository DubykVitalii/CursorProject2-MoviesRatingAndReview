package com.example.cursorproject2moviesratingandreview.models.enums;

public enum Category {
    SCI_FI("Science fiction"),
    ACT("Action"),
    DRAM("Drama"),
    ROM("Romance"),
    COM("Comedy"),
    DET("Detective"),
    THR("Thriller"),
    HOR("Horror");


    private String fullName;

    public String getFullName() {
        return fullName;
    }

    Category(String fullName) {
        this.fullName = fullName;
    }
}
