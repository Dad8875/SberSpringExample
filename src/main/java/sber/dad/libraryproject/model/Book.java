package sber.dad.libraryproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_gen")
    @SequenceGenerator(name = "books_gen", sequenceName = "books_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String bookTitle;

    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;

    @Column(name= "page_count")
    private Integer pageCount;

    @Column(name= "amount",  nullable = false)
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
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToMany
    private Set<Author> authors;

}

