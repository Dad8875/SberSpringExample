package sber.dad.libraryproject.service;

import org.springframework.stereotype.Service;
import sber.dad.libraryproject.dto.BookRentInfoDTO;
import sber.dad.libraryproject.mapper.BookRentInfoMapper;
import sber.dad.libraryproject.model.BookRentInfo;
import sber.dad.libraryproject.repository.BookRentInfoRepository;

@Service
public class BookRentInfoService extends GenericService<BookRentInfo, BookRentInfoDTO> {

    private BookRentInfoRepository bookRentInfoRepository;

    public BookRentInfoService(BookRentInfoRepository bookRentInfoRepository, BookRentInfoMapper bookRentInfoMapper) {
        super(bookRentInfoRepository, bookRentInfoMapper);
    }
}
