package com.example.cursorproject2moviesratingandreview.repository;


import com.example.cursorproject2moviesratingandreview.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    User getUserByUsername(String username);
}
