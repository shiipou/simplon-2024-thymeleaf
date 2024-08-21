package fr.simplon.spring_security.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;

import fr.simplon.spring_security.dto.RegisterDto;
import fr.simplon.spring_security.dto.UserDto;
import fr.simplon.spring_security.model.User;

public interface UserService {
    void saveUser(UserDto user);
    void saveUser(RegisterDto userMapping);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
    Optional<User> from(Authentication authentication);

}
