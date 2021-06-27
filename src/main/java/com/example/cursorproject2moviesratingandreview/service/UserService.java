package com.example.cursorproject2moviesratingandreview.service;

import com.example.cursorproject2moviesratingandreview.models.User;
import com.example.cursorproject2moviesratingandreview.models.enums.UserPermission;
import com.example.cursorproject2moviesratingandreview.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;
    static AccessDeniedException ACCESS_DENIED = new AccessDeniedException("Access denied");

    @Autowired
    public UserService(UserRepo userRepo, @Lazy BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    public void createDtoUser(String username, String password) {
        User user = new User(username, encoder.encode(password), UserPermission.ROLE_USER);
        userRepo.save(user);
    }

    public UserDetails login(String username, String password) {
        var user = userRepo.findByUsername(username).orElseThrow(() -> ACCESS_DENIED);
        if (!encoder.matches(password, user.getPassword()))
            throw ACCESS_DENIED;
        return user;
    }

    public User getUserById(Long id) {
        return userRepo.getById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow();
    }
}
