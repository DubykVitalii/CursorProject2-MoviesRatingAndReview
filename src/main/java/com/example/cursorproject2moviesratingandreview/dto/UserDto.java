package com.example.cursorproject2moviesratingandreview.dto;

import com.example.cursorproject2moviesratingandreview.models.enums.UserPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private UserPermission userPermission;
}
