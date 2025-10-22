package sber.dad.libraryproject.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sber.dad.libraryproject.dto.BookDTO;
import sber.dad.libraryproject.model.Book;
import sber.dad.libraryproject.model.GenericModel;
import sber.dad.libraryproject.repository.AuthorRepository;
import sber.dad.libraryproject.utils.DateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class BookMapper extends GenericMapper<Book, BookDTO> {

    private final AuthorRepository authorRepository;

    protected BookMapper(ModelMapper modelMapper, AuthorRepository authorRepository) {
        super(modelMapper, Book.class, BookDTO.class);
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Book.class, BookDTO.class)
                .addMappings(m -> m.skip(BookDTO::setAuthorsIds)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(BookDTO.class, Book.class)
                .addMappings(m -> m.skip(Book::setAuthors)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Book::setPublishDate)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificField(BookDTO source, Book destination) {
        if (!Objects.isNull(source.getAuthorsIds())) {
            destination.setAuthors(new HashSet<>(authorRepository.findAllById(source.getAuthorsIds())));
        } else {
            destination.setAuthors(Collections.emptySet());
        }
        destination.setPublishDate(DateFormatter.formatStringToDate(source.getPublishDate()));
    }

    @Override
    protected void mapSpecificField(Book source, BookDTO destination) {
        destination.setAuthorsIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Book entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getAuthors())
                ? null
                : entity.getAuthors().stream().map(GenericModel::getId).collect(Collectors.toSet());
    }
}
