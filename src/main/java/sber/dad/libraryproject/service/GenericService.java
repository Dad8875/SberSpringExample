package sber.dad.libraryproject.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import sber.dad.libraryproject.dto.GenericDTO;
import sber.dad.libraryproject.mapper.GenericMapper;
import sber.dad.libraryproject.model.GenericModel;
import sber.dad.libraryproject.repository.GenericRepository;

import java.util.List;


@Service
public abstract class GenericService<T extends GenericModel, N extends GenericDTO> {

    protected final GenericRepository<T> repository;
    protected final GenericMapper<T, N> mapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericService(GenericRepository<T> repository, GenericMapper<T, N> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<N> getAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public N getOne(Long id) {
        return mapper.toDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found!")));
    }

    public N create(N n) {
        return mapper.toDTO(repository.save(mapper.toEntity(n)));
    }

    public N update(N n) {
        return mapper.toDTO(repository.save(mapper.toEntity(n)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}


