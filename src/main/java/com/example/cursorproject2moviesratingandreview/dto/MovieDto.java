package com.example.cursorproject2moviesratingandreview.dto;

import com.example.cursorproject2moviesratingandreview.models.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private String name;
    private Category category;
    private String director;
    private String description;
    private List<ReviewDto> reviewList;
    private RateDto rate;
}
