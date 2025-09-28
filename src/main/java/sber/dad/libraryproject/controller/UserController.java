package sber.dad.libraryproject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sber.dad.libraryproject.dto.UserDTO;
import sber.dad.libraryproject.model.User;
import sber.dad.libraryproject.service.UserService;

@Tag(name = "Пользователи", description = "Контроллер для работы с пользователями")
@RequestMapping("/users")
@RestController
public class UserController extends GenericController<User, UserDTO> {

    public UserController(UserService userService) {
        super(userService);
    }
}

