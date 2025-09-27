package sber.dad.libraryproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO extends GenericDTO {

    private String fio;

    private LocalDate birthDate;

    private String description;

    private Set<Long> booksIds;

}
