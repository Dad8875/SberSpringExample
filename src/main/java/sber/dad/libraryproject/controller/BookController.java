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

@Tag(name = "Книги", description = "Контроллер для работы с книгами")
@RequestMapping("/books")
@RestController
public class BookController extends GenericController<Book> {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        super(bookRepository);
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Operation(description = "Добавить автора к книге", method = "addAuthor")
    @RequestMapping(value = "/addAuthor", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addAuthor(@RequestParam(value = "bookId") Long bookId,
                                          @RequestParam(value = "authorId") Long authorId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id: " + bookId + " not found!"));
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id: " + authorId + " not found!"));

        book.getAuthors().add(author);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(book));
    }
}

