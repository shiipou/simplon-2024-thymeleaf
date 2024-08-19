package fr.simplon.thymeleaf.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.simplon.thymeleaf.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  public Optional<User> findByUsernameAndPassword(String username, String password);
  public Optional<User> findByEmailAndPassword(String email, String password);
}
