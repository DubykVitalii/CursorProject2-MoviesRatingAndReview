package com.example.cursorproject2moviesratingandreview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDto {
    private double rateValue;
    private int countOfVotes;

}
