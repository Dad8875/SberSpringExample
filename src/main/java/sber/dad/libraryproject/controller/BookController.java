package sber.dad.libraryproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sber.dad.libraryproject.model.Book;
import sber.dad.libraryproject.repository.BookRepository;


@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(name = "Книги", description = "Контроллер для работы с книгами")
public class BookController {

    private final BookRepository repository;

    @Operation(description = "Получить книгу по ID", method = "getBookById")
    @RequestMapping(value = "/getById", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBookById(@RequestParam(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book with id " + id + " not found!")
        ));
    }
}
