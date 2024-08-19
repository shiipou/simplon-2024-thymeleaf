package fr.simplon.thymeleaf.message;

import org.springframework.lang.NonNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class UserRegisterMapping {
  @NonNull
  private String username;
  @NonNull
  private String email;
  @NonNull
  private String password;
  @NonNull
  private String passwordConfirm;
}
