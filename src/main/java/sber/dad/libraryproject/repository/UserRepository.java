package sber.dad.libraryproject.repository;

import org.springframework.stereotype.Repository;
import sber.dad.libraryproject.model.User;

@Repository
public interface UserRepository extends GenericRepository<User> {

    User findUserByLogin(String login);

    User findUserByEmail(String email);
}
