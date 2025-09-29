package sber.dad.libraryproject.service;

import org.springframework.stereotype.Service;
import sber.dad.libraryproject.dto.BookDTO;
import sber.dad.libraryproject.dto.BookWithAuthorsDTO;
import sber.dad.libraryproject.mapper.BookMapper;
import sber.dad.libraryproject.mapper.BookWithAuthorsMapper;
import sber.dad.libraryproject.model.Book;
import sber.dad.libraryproject.repository.BookRepository;

import java.util.List;


@Service
public class BookService extends GenericService<Book, BookDTO> {

    private final BookRepository bookRepository;
    private final BookWithAuthorsMapper bookWithAuthorsMapper;

    protected BookService(BookRepository bookRepository, BookMapper bookMapper, BookWithAuthorsMapper bookWithAuthorsMapper) {
        super(bookRepository, bookMapper);
        this.bookRepository = bookRepository;
        this.bookWithAuthorsMapper = bookWithAuthorsMapper;
    }

    public List<BookWithAuthorsDTO> getAllBooksWithAuthors() {
        return bookWithAuthorsMapper.toDTOs(bookRepository.findAll());
    }

}
