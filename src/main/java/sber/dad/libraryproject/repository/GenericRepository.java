package sber.dad.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import sber.dad.libraryproject.model.GenericModel;

@NoRepositoryBean
public interface GenericRepository<T extends GenericModel> extends JpaRepository<T, Long> {
}
