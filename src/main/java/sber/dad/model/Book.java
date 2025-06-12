package sber.dad.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

//    @Setter(AccessLevel.NONE)
    private Integer bookId;

    private String bookTitle;

    private String bookAuthor;

    private Date dateAdded;
}
