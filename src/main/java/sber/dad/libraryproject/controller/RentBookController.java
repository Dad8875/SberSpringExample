package sber.dad.libraryproject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sber.dad.libraryproject.dto.BookRentInfoDTO;
import sber.dad.libraryproject.model.BookRentInfo;
import sber.dad.libraryproject.service.BookRentInfoService;

@Tag(name = "Аренда книг", description = "Контроллер для работы с арендой/выдачей книг пользователю")
@RequestMapping("/rent/info")
@RestController
public class RentBookController extends GenericController<BookRentInfo, BookRentInfoDTO> {

    public RentBookController(BookRentInfoService bookRentInfoService) {
        super(bookRentInfoService);
    }

}

