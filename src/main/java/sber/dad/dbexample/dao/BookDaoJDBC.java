package sber.dad.dbexample.dao;

import sber.dad.dbexample.db.DBApp;
import sber.dad.model.Book;

import java.sql.*;

public class BookDaoJDBC {

    public Book findBookById(int id) {
        try (Connection connection = DBApp.INSTANCE.newConnection()) {
            if (connection == null)
                System.out.println("connection is null");
            else
                System.out.println("connection is open");

            PreparedStatement ps = connection.prepareStatement("select * from books where id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("id"));
                book.setBookTitle(rs.getString("title"));
                book.setBookAuthor(rs.getString("author"));
                book.setDateAdded(rs.getDate("date_added"));
                System.out.println(book);
                return book;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}

