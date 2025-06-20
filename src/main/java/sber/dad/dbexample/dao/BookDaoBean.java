package sber.dad.dbexample.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sber.dad.dbexample.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@RequiredArgsConstructor
@Component
public class BookDaoBean {

    private final Connection connection;

    public Book findBookById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from books where id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Book book = new Book();
        if (resultSet.next()) {
            book.setBookId(resultSet.getInt("id"));
            book.setBookTitle(resultSet.getString("title"));
            book.setBookAuthor(resultSet.getString("author"));
            book.setDateAdded(resultSet.getDate("date_added"));
            System.out.println(book);
        }
        return book;
    }
}
