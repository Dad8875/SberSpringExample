package sber.dad.libraryproject.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import sber.dad.libraryproject.dto.BookRentInfoDTO;
import sber.dad.libraryproject.model.BookRentInfo;
import sber.dad.libraryproject.repository.BookRepository;
import sber.dad.libraryproject.repository.UserRepository;

import java.util.Set;


@Component
public class BookRentInfoMapper extends GenericMapper<BookRentInfo, BookRentInfoDTO> {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    protected BookRentInfoMapper(ModelMapper modelMapper, BookRepository bookRepository, UserRepository userRepository) {
        super(modelMapper, BookRentInfo.class, BookRentInfoDTO.class);
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setupMapper() {
        super.modelMapper.createTypeMap(BookRentInfo.class, BookRentInfoDTO.class)
                .addMappings(m -> m.skip(BookRentInfoDTO::setUserId)).setPostConverter(toDTOConverter())
                .addMappings(m -> m.skip(BookRentInfoDTO::setBookId)).setPostConverter(toDTOConverter());

        super.modelMapper.createTypeMap(BookRentInfoDTO.class, BookRentInfo.class)
                .addMappings(m -> m.skip(BookRentInfo::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(BookRentInfo::setBook)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificField(BookRentInfoDTO source, BookRentInfo destination) {
        destination.setBook(bookRepository.findById(source.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book Not Found")));
        destination.setUser(userRepository.findById(source.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User Not Found")));
    }

    @Override
    protected void mapSpecificField(BookRentInfo source, BookRentInfoDTO destination) {
        destination.setUserId(source.getUser().getId());
        destination.setBookId(source.getBook().getId());
    }

    @Override
    protected Set<Long> getIds(BookRentInfo entity) {
        throw  new UnsupportedOperationException("Not supported yet.");
    }

}

