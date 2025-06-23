package sber.dad.libraryproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_gen")
    @SequenceGenerator(name = "authors_gen", sequenceName = "authors_seq")
    @Column(nullable = false)
    private Long id;

    @Column(name = "fio", nullable = false)
    private String fio;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "description")
    private String description;

    @ManyToMany
    private Set<Book> books;

}
