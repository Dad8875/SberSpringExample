package sber.dad.libraryproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
@SequenceGenerator(name = "default_generator", sequenceName = "book_seq", allocationSize = 1)
public class Book extends GenericModel{

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "storage_place")
    private String storagePlace;

    @Column(name = "online_copy_path")
    private String onlineCopy;

    @Column(name = "description")
    private String description;

    @Column(name = "publish")
    private String publish;

    @Column(name = "genre")
    @Enumerated
    private Genre genre;

    @ManyToMany
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            foreignKey = @ForeignKey(name = "FK_BOOKS_AUTHORS"),
            inverseJoinColumns = @JoinColumn(name = "author_id"),
            inverseForeignKey = @ForeignKey(name = "FK_AUTHORS_BOOKS"))
    private Set<Author> authors;

}

