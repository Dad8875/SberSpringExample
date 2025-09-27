package sber.dad.libraryproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sber.dad.libraryproject.dto.GenericDTO;
import sber.dad.libraryproject.model.GenericModel;
import sber.dad.libraryproject.service.GenericService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public abstract class GenericController<T extends GenericModel, N extends GenericDTO> {

    private final GenericService<T, N> genericService;

    public GenericController(GenericService<T, N> genericService) {
        this.genericService = genericService;
    }

    @Operation(description = "Получить все записи", method = "getAll")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<N>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(genericService.getAll());
    }

    @Operation(description = "Получить по ID", method = "getById")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(genericService.getOne(id));
    }

    @Operation(description = "Добавить", method = "create")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> create(@RequestBody N n) {
        n.setCreatedWhen(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(genericService.create(n));
    }

    @Operation(description = "Обновить данные", method = "update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> update(@RequestBody N n, @RequestParam(value = "id") Long id) {
        n.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(genericService.update(n));
    }

    @Operation(description = "Удалить", method = "delete")
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
        genericService.delete(id);
    }

}

