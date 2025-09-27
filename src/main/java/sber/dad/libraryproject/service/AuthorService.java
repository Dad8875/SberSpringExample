package sber.dad.libraryproject.service;

import org.springframework.stereotype.Service;
import sber.dad.libraryproject.dto.AuthorDTO;
import sber.dad.libraryproject.mapper.AuthorMapper;
import sber.dad.libraryproject.model.Author;
import sber.dad.libraryproject.repository.AuthorRepository;


@Service
public class AuthorService extends GenericService<Author, AuthorDTO> {

    protected AuthorRepository authorRepository;

    protected AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        super(authorRepository, authorMapper);
    }
}
