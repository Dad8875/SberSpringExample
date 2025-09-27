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
import sber.dad.libraryproject.dto.BookDTO;
import sber.dad.libraryproject.model.Author;
import sber.dad.libraryproject.model.Book;
import sber.dad.libraryproject.repository.AuthorRepository;
import sber.dad.libraryproject.service.BookService;

@Tag(name = "Книги", description = "Контроллер для работы с книгами")
@RequestMapping("/books")
@RestController
public class BookController extends GenericController<Book, BookDTO> {

    public BookController(BookService bookService) {
        super(bookService);
    }
//
//
//    @Operation(description = "Добавить автора к книге", method = "addAuthor")
//    @RequestMapping(value = "/addAuthor", method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<BookDTO> addAuthor(@RequestParam(value = "bookId") Long bookId,
//                                          @RequestParam(value = "authorId") Long authorId) {
//
//        BookDTO book = bookService.getOne(bookId);
//        Author author = authorRepository.findById(authorId)
//                .orElseThrow(() -> new ResourceNotFoundException("Author with id: " + authorId + " not found!"));
//
//        book.getAuthorsIds().add(author.getId());
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
//    }
}

