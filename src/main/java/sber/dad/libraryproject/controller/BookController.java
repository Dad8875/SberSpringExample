package sber.dad.libraryproject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sber.dad.libraryproject.model.Book;
import sber.dad.libraryproject.repository.BookRepository;

@Tag(name = "Книги", description = "Контроллер для работы с книгами")
@RequestMapping("/books")
@RestController
public class BookController extends GenericController<Book> {

    public BookController(BookRepository repository) {
        super(repository);
    }
}

