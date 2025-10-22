package sber.dad.libraryproject.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sber.dad.libraryproject.dto.UserDTO;
import sber.dad.libraryproject.model.GenericModel;
import sber.dad.libraryproject.model.User;
import sber.dad.libraryproject.repository.BookRentInfoRepository;
import sber.dad.libraryproject.utils.DateFormatter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class UserMapper extends GenericMapper<User, UserDTO> {

    private final BookRentInfoRepository bookRentInfoRepository;

    protected UserMapper(ModelMapper modelMapper, BookRentInfoRepository bookRentInfoRepository) {
        super(modelMapper, User.class, UserDTO.class);
        this.bookRentInfoRepository = bookRentInfoRepository;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO::setUserBooksRent)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(UserDTO.class, User.class)
                .addMappings(m -> m.skip(User::setBookRentInfos)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(User::setBirthDate)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificField(UserDTO source, User destination) {
        if (!Objects.isNull(source.getUserBooksRent())) {
            destination.setBookRentInfos(new HashSet<>(bookRentInfoRepository.findAllById(source.getUserBooksRent())));
        } else {
            destination.setBookRentInfos(Collections.emptySet());
        }
        destination.setBirthDate(DateFormatter.formatStringToDate(source.getBirthDate()));
    }

    @Override
    protected void mapSpecificField(User source, UserDTO destination) {
        destination.setUserBooksRent(getIds(source));
    }

    @Override
    protected Set<Long> getIds(User entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getBookRentInfos())
                ? null
                : entity.getBookRentInfos().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }

}

