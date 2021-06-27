package com.example.cursorproject2moviesratingandreview.models;

import com.example.cursorproject2moviesratingandreview.models.enums.Category;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "Name")
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "Director")
    private String director;
    @Column(name = "Description")
    private String description;


    public Movie(String name, Category category, String director, String description) {
        this.name = name;
        this.category = category;
        this.director = director;
        this.description = description;
    }

    public Movie() {
    }


    //    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
//    List<Review> reviews;


}

