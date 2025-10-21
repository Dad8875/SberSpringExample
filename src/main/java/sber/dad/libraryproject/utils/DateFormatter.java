package sber.dad.libraryproject.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    private DateFormatter() {}

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static LocalDate formatStringToDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

}
