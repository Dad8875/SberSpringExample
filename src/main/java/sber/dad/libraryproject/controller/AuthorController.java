package sber.dad.libraryproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sber.dad.libraryproject.model.Author;
import sber.dad.libraryproject.model.Book;
import sber.dad.libraryproject.repository.AuthorRepository;
import sber.dad.libraryproject.repository.BookRepository;

@Tag(name = "Авторы", description = "Контроллер для работы с авторами")
@RequestMapping("/author")
@RestController
public class AuthorController extends GenericController<Author> {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
        super(authorRepository);
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Operation(description = "Добавить книгу к автору", method = "addBook")
    @RequestMapping(value = "/addBook", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> addBook(@RequestParam(value = "bookId") Long bookId,
                                          @RequestParam(value = "authorId") Long authorId) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id: " + authorId + " not found!"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id: " + bookId + " not found!"));

        author.getBooks().add(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(authorRepository.save(author));
    }
}

