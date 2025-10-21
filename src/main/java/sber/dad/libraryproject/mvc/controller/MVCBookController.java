package sber.dad.libraryproject.mvc.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sber.dad.libraryproject.dto.BookDTO;
import sber.dad.libraryproject.dto.BookWithAuthorsDTO;
import sber.dad.libraryproject.service.BookService;

import java.util.List;


@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
@Hidden
public class MVCBookController {

    private final BookService bookService;

    @GetMapping("")
    public String getAll(Model model) {
        List<BookWithAuthorsDTO> booksWithAuthors = bookService.getAllBooksWithAuthors();
        model.addAttribute("books", booksWithAuthors);
        return "books/viewAllBooks";
    }

    @GetMapping("/add")
    public String create() {
        return "books/addBook";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("bookForm")BookDTO bookDTO) {
        bookService.create(bookDTO);
        return "redirect:/books";
    }
}
