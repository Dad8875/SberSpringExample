package sber.dad;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import sber.dad.dbexample.dao.BookDaoBean;
import sber.dad.config.MyDBConfigContext;
import sber.dad.model.Book;

@SpringBootApplication
@RequiredArgsConstructor
public class SberSpringExampleApplication implements CommandLineRunner {

    private final BookDaoBean bookDaoBean;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SberSpringExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Book bookById = bookDaoBean.findBookById(4);

        namedParameterJdbcTemplate.query("select * from books", (rs, rowNum) ->
                new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDate("date_added"))
        ).forEach(System.out::println);
    }
}

