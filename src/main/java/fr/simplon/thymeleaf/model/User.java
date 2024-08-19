package fr.simplon.thymeleaf.model;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.NonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  @NonNull
  private String username;

  @NonNull
  private String email;

  private String password;

  @ColumnDefault("0")
  private UserRole role;
}
