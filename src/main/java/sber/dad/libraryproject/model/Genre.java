package sber.dad.libraryproject.model;

import lombok.Getter;

@Getter
public enum Genre {

    FANTASY("фэнтези"),
    SCIENCE_FICTION("научная фантастика"),
    DRAMA("драма"),
    NOVEL("роман");

    private final String description;

    Genre(String description) {
        this.description = description;
    }
}

