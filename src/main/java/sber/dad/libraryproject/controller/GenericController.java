package sber.dad.libraryproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sber.dad.libraryproject.model.GenericModel;
import sber.dad.libraryproject.repository.GenericRepository;

import java.time.LocalDateTime;

@RestController
public abstract class GenericController<T extends GenericModel> {

    private final GenericRepository<T> repository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericController(GenericRepository<T> repository) {
        this.repository = repository;
    }

    @Operation(description = "Получить по ID", method = "getEntityById")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> geEntityById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity with id: " + id + " not found!")
        ));
    }

    @Operation(description = "Добавить", method = "createEntity")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> createEntity(@RequestBody T t) {
        t.setCreatedWhen(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(t));
    }

    @Operation(description = "Обновить данные", method = "updateEntity")
    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> updateEntity(@RequestBody T t, @RequestParam(value = "id") Long id) {
        t.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(t));
    }

    @Operation(description = "Удалить", method = "deleteEntity")
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public void deleteEntity(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
    }

}

