package sber.dad.libraryproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sber.dad.libraryproject.model.Genre;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class BookDTO extends GenericDTO {

    private String title;
    private String publishDate;
    private Integer pageCount;
    private Integer amount;
    private String storagePlace;
    private String onlineCopy;
    private String description;
    private Genre genre;
    private Set<Long> authorsIds;

}

