package sber.dad.libraryproject.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sber.dad.libraryproject.dto.BookWithAuthorsDTO;
import sber.dad.libraryproject.model.Book;
import sber.dad.libraryproject.model.GenericModel;
import sber.dad.libraryproject.repository.AuthorRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class BookWithAuthorsMapper extends GenericMapper<Book, BookWithAuthorsDTO> {

    private final AuthorRepository authorRepository;

    public BookWithAuthorsMapper(ModelMapper modelMapper, AuthorRepository authorRepository) {
        super(modelMapper, Book.class, BookWithAuthorsDTO.class);
        this.authorRepository = authorRepository;
    }

    @Override
    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Book.class, BookWithAuthorsDTO.class)
                .addMappings(m -> m.skip(BookWithAuthorsDTO::setAuthorsIds))
                .setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(BookWithAuthorsDTO.class, Book.class)
                .addMappings(m -> m.skip(Book::setAuthors))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificField(BookWithAuthorsDTO source, Book destination) {
        destination.setAuthors(new HashSet<>(authorRepository.findAllById(source.getAuthorsIds())));
    }

    @Override
    protected void mapSpecificField(Book source, BookWithAuthorsDTO destination) {
        destination.setAuthorsIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Book entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getId())
                ? null
                : entity.getAuthors().stream()
                .map(GenericModel::getId).collect(Collectors.toSet());
    }
}
