package sber.dad.libraryproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authors")
@SequenceGenerator(name = "default_generator", sequenceName = "author_seq", allocationSize = 1)
public class Author extends GenericModel {

    @Column(name = "fio", nullable = false)
    private String fio;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

}
