package sber.dad.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sber.dad.libraryproject.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
