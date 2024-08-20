package fr.simplon.spring_security.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotEmpty(message = "Username must not be empty")
    private String username;

    // @NotEmpty(message = "Email must not be empty")
    // private String email;

    @NotEmpty(message = "Password must not be empty")
    private String password;
}
