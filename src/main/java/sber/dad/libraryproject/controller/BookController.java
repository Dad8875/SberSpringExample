package sber.dad.libraryproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sber.dad.libraryproject.model.Book;
import sber.dad.libraryproject.repository.BookRepository;

import java.time.LocalDateTime;


@Tag(name = "Книги", description = "Контроллер для работы с книгами")
@RequiredArgsConstructor
@RequestMapping("/books")
@RestController
public class BookController {

    private final BookRepository repository;

    @Operation(description = "Получить книгу по ID", method = "getBookById")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book with id " + id + " not found!")
        ));
    }

    @Operation(description = "Добавить новую книгу", method = "createBook")
    @RequestMapping(value = "/add", method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        book.setCreatedWhen(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(book));
    }

    @Operation(description = "Обновить данные по книге", method = "updateBook")
    @RequestMapping(value = "/update", method = RequestMethod.PUT,
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @RequestParam(value = "id") Long id) {
        book.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(book));
    }

    @Operation(description = "Удалить книгу", method = "deleteBook")
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
    }

}
