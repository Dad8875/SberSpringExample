package sber.dad.libraryproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_rent_info")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "book_rent_info_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class BookRentInfo extends GenericModel {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "FK_RENT_BOOK_INFO_BOOK"))
    private Book book;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_RENT_BOOK_INFO_USER"))
    private User user;

    @Column(name = "rent_time", nullable = false)
    private LocalDateTime rentDate;

    @Column(name = "rent_period", nullable = false)
    private Integer rentPeriod;

    @Column(name = "return_date", nullable = false)
    private LocalDateTime returnDate;

    @Column(name = "returned")
    private Boolean returned;

}
