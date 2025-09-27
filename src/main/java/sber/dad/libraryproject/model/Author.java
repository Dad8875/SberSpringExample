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
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Author extends GenericModel {

    @Column(name = "fio", nullable = false)
    private String fio;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            foreignKey = @ForeignKey(name = "FK_AUTHORS_BOOKS"),
            inverseJoinColumns = @JoinColumn(name = "book_id"),
            inverseForeignKey = @ForeignKey(name = "FK_BOOKS_AUTHORS"))
    private Set<Book> books;
}
