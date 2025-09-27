package sber.dad.libraryproject.service;

import org.springframework.stereotype.Service;
import sber.dad.libraryproject.dto.BookDTO;
import sber.dad.libraryproject.mapper.BookMapper;
import sber.dad.libraryproject.model.Book;
import sber.dad.libraryproject.repository.BookRepository;


@Service
public class BookService extends GenericService<Book, BookDTO> {

    protected BookRepository bookRepository;

    protected BookService(BookRepository bookRepository, BookMapper bookMapper) {
        super(bookRepository, bookMapper);
        this.bookRepository = bookRepository;
    }
}
