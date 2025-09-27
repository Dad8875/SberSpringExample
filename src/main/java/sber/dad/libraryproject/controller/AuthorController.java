package sber.dad.libraryproject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sber.dad.libraryproject.dto.AuthorDTO;
import sber.dad.libraryproject.model.Author;
import sber.dad.libraryproject.service.AuthorService;

@Tag(name = "Авторы", description = "Контроллер для работы с авторами")
@RequestMapping("/author")
@RestController
public class AuthorController extends GenericController<Author, AuthorDTO> {

    public AuthorController(AuthorService authorService) {
        super(authorService);
    }

}

